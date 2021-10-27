package lk.coolbreez.www.service.implement;

import lk.coolbreez.www.dto.security.UserDetailsDto;
import lk.coolbreez.www.model.user.AuthorityModel;
import lk.coolbreez.www.model.user.UserModel;
import lk.coolbreez.www.repository.UserRepository.AuthorityRepository;
import lk.coolbreez.www.repository.UserRepository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<UserModel> userModel = userRepository.findById(userEmail);
        Optional<AuthorityModel> authorityModel = authorityRepository.findById(userEmail);
        if (userModel.isPresent() && authorityModel.isPresent()) {
            return new UserDetailsDto(userModel.get().getUsername(),
                    userModel.get().getPassword(),
                    authorityModel.get().getAuthorities(),
                    userModel.get().isEnabled()
            );
        } else {
            throw new UsernameNotFoundException("User does not exists!");
        }
    }
}
