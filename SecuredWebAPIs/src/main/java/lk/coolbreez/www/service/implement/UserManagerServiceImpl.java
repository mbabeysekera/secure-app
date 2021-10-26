package lk.coolbreez.www.service.implement;

import lk.coolbreez.www.dto.request.admin.CreateUserRequestDto;
import lk.coolbreez.www.dto.request.admin.DeleteUserRequestDto;
import lk.coolbreez.www.dto.request.admin.UpdateAuthorityRequestDto;
import lk.coolbreez.www.dto.request.admin.UpdateUserRequestDto;
import lk.coolbreez.www.dto.response.admin.*;
import lk.coolbreez.www.model.user.AuthorityModel;
import lk.coolbreez.www.model.user.LoginDetailsModel;
import lk.coolbreez.www.model.user.RefreshTokenModel;
import lk.coolbreez.www.model.user.UserModel;
import lk.coolbreez.www.repository.UserRepository.AuthorityRepository;
import lk.coolbreez.www.repository.UserRepository.LoginDetailsRepository;
import lk.coolbreez.www.repository.UserRepository.RefreshTokenRepository;
import lk.coolbreez.www.repository.UserRepository.UserRepository;
import lk.coolbreez.www.service.UserManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserManagerServiceImpl implements UserManagerService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final LoginDetailsRepository loginDetailsRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String NO_USER = "User does not exists!";

    public UserManagerServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository,
                                  LoginDetailsRepository loginDetailsRepository,
                                  RefreshTokenRepository refreshTokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.loginDetailsRepository = loginDetailsRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public GetUserResponseDto getUserDetails(String username) throws UsernameNotFoundException {
        Optional<UserModel> userModel = userRepository.findById(username);
        Optional<AuthorityModel> authorityModel = authorityRepository.findById(username);
        Optional<LoginDetailsModel> loginDetailsModel = loginDetailsRepository.findById(username);
        if (userModel.isPresent() && authorityModel.isPresent() && loginDetailsModel.isPresent()) {
            return new GetUserResponseDto(userModel.get().getMobile(), userModel.get().isEnabled(),
                    authorityModel.get().getAuthorities(), authorityModel.get().getRoles(),
                    loginDetailsModel.get().getLastLogin()
            );
        } else {
            throw new UsernameNotFoundException(NO_USER);
        }
    }

    @Override
    public CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto) throws IllegalArgumentException {
        if (userRepository.findById(createUserRequestDto.getUsername()).isEmpty()) {
            userRepository.save(new UserModel(
                    createUserRequestDto.getUsername(),
                    createUserRequestDto.getMobile(),
                    passwordEncoder.encode(createUserRequestDto.getPassword()),
                    createUserRequestDto.isActivated())
            );
            authorityRepository.save(new AuthorityModel(
                    createUserRequestDto.getUsername(),
                    createUserRequestDto.getAuthorities(),
                    createUserRequestDto.getRoles()
            ));
            loginDetailsRepository.save(new LoginDetailsModel(
                    createUserRequestDto.getUsername(),
                    null,
                    null,
                    null
            ));
            refreshTokenRepository.save(new RefreshTokenModel(
                    createUserRequestDto.getUsername(),
                    null,
                    null
            ));
            return new CreateUserResponseDto(createUserRequestDto.getUsername(), "User created.");
        } else {
            throw new IllegalArgumentException("Username already exists!");
        }
    }

    @Override
    public UpdateUserResponseDto updateUser(UpdateUserRequestDto updateUserRequestDto)
            throws UsernameNotFoundException {
        Optional<UserModel> userModel = userRepository.findById(updateUserRequestDto.getAdminName());
        log.info(updateUserRequestDto.getUsername() + "|UPDATED_BY|" + updateUserRequestDto.getAdminName());
        if (userModel.isPresent() && (passwordEncoder.matches(updateUserRequestDto.getAdminPassword(),
                userModel.get().getPassword()))) {
            userModel = userRepository.findById(updateUserRequestDto.getUsername());
            if (userModel.isPresent()) {
                if (updateUserRequestDto.getUserPassword() != null) {
                    userModel.get().setPassword(passwordEncoder.encode(updateUserRequestDto.getUserPassword()));
                    log.info(updateUserRequestDto.getUsername() + " password updated");
                }
                if (updateUserRequestDto.getMobile() != null) {
                    userModel.get().setMobile(updateUserRequestDto.getMobile());
                    log.info(updateUserRequestDto.getUsername() + " mobile updated");
                }
                log.info(updateUserRequestDto.getUsername() + " status updated");
                userModel.get().setEnabled(updateUserRequestDto.isActivated());
                userRepository.save(userModel.get());
                return new UpdateUserResponseDto(userModel.get().getUsername(), "User successfully updated!");
            } else {
                throw new UsernameNotFoundException(NO_USER);
            }
        } else {
            throw new UsernameNotFoundException("Admin validation failed!");
        }
    }

    @Override
    public UpdateAuthorityResponseDto updateAuthority(UpdateAuthorityRequestDto updateAuthorityRequestDto)
            throws UsernameNotFoundException {
        log.info(updateAuthorityRequestDto.getUsername() + "|AUTHORIZED_BY|" + updateAuthorityRequestDto
                .getAdminName());
        Optional<AuthorityModel> authorityModel = authorityRepository
                .findById(updateAuthorityRequestDto.getUsername());
        if (authorityModel.isPresent()) {
            if (updateAuthorityRequestDto.getRoles() != null) {
                authorityModel.get().setRoles(updateAuthorityRequestDto.getRoles());
                log.info("User roles updated");
            }
            if (updateAuthorityRequestDto.getAuthorities() != null) {
                authorityModel.get().setAuthorities(updateAuthorityRequestDto.getAuthorities());
                log.info("User authorities updated");
            }
            authorityRepository.save(authorityModel.get());
            return new UpdateAuthorityResponseDto(updateAuthorityRequestDto.getUsername(),
                    "User authorization details updated.");
        } else {
            throw new UsernameNotFoundException(NO_USER);
        }
    }

    @Override
    public DeleteUserResponseDto deleteUser(DeleteUserRequestDto deleteUserRequestDto) throws UsernameNotFoundException{
        log.info(deleteUserRequestDto.getUsername() + "|DELETED_BY|" + deleteUserRequestDto.getAdminName());
        Optional<UserModel> userModel = userRepository.findById(deleteUserRequestDto.getUsername());
        if (userModel.isPresent()) {
            userRepository.deleteById(deleteUserRequestDto.getUsername());
            authorityRepository.deleteById(deleteUserRequestDto.getUsername());
            loginDetailsRepository.deleteById(deleteUserRequestDto.getUsername());
            refreshTokenRepository.deleteById(deleteUserRequestDto.getUsername());
        } else {
            throw new UsernameNotFoundException(NO_USER);
        }
        return new DeleteUserResponseDto(deleteUserRequestDto.getUsername(), "User successfully deleted!");
    }
}
