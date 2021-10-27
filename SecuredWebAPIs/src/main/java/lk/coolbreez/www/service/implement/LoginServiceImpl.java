package lk.coolbreez.www.service.implement;

import lk.coolbreez.www.dto.RefreshTokenDto;
import lk.coolbreez.www.dto.request.LoginRequestDto;
import lk.coolbreez.www.dto.response.LoginResponseDto;
import lk.coolbreez.www.model.user.LoginDetailsModel;
import lk.coolbreez.www.model.user.RefreshTokenModel;
import lk.coolbreez.www.repository.UserRepository.LoginDetailsRepository;
import lk.coolbreez.www.service.LoginService;
import lk.coolbreez.www.utility.JwtUtility;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtility jwtUtility;
    private final LoginDetailsRepository loginDetailsRepository;
    private final RefreshTokenServiceImpl refreshTokenService;
    private final ModelMapper modelMapper;

    public LoginServiceImpl(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService,
                            JwtUtility jwtUtility, LoginDetailsRepository loginDetailsRepository,
                            RefreshTokenServiceImpl refreshTokenService, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtility = jwtUtility;
        this.loginDetailsRepository = loginDetailsRepository;
        this.refreshTokenService = refreshTokenService;
        this.modelMapper = modelMapper;
    }

    @Override
    public LoginResponseDto authenticateUser(LoginRequestDto loginRequestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),
                            loginRequestDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect credentials!");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getUsername());
        final String accessToken = jwtUtility.generateToken(userDetails);
        final RefreshTokenModel refreshTokenModel = refreshTokenService
                .createRefreshToken(loginRequestDto.getUsername());
        loginDetailsRepository.save(new LoginDetailsModel(loginRequestDto.getUsername(), accessToken,
                new Date(System.currentTimeMillis()), jwtUtility.extractExpiration(accessToken)));
        return new LoginResponseDto(loginRequestDto.getUsername(), accessToken, "Bearer",
                modelMapper.map(refreshTokenModel, RefreshTokenDto.class));
    }
}
