package lk.coolbreez.www.service.implement;

import lk.coolbreez.www.dto.RefreshTokenDto;
import lk.coolbreez.www.dto.request.RefreshTokenRequestDto;
import lk.coolbreez.www.dto.response.RefreshTokenResponseDto;
import lk.coolbreez.www.exception.RefreshTokenValidationException;
import lk.coolbreez.www.model.user.LoginDetailsModel;
import lk.coolbreez.www.model.user.RefreshTokenModel;
import lk.coolbreez.www.repository.UserRepository.LoginDetailsRepository;
import lk.coolbreez.www.service.AuthorizationService;
import lk.coolbreez.www.utility.JwtUtility;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private final RefreshTokenServiceImpl refreshTokenService;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtility jwtUtility;
    private final ModelMapper modelMapper;
    private final LoginDetailsRepository loginDetailsRepository;

    public AuthorizationServiceImpl(RefreshTokenServiceImpl refreshTokenService,
                                    UserDetailsServiceImpl userDetailsService, JwtUtility jwtUtility,
                                    ModelMapper modelMapper, LoginDetailsRepository loginDetailsRepository) {
        this.refreshTokenService = refreshTokenService;
        this.userDetailsService = userDetailsService;
        this.jwtUtility = jwtUtility;
        this.modelMapper = modelMapper;
        this.loginDetailsRepository = loginDetailsRepository;
    }

    @Override
    public RefreshTokenResponseDto createNewAccessToken(RefreshTokenRequestDto tokenModel)
            throws RefreshTokenValidationException {
        RefreshTokenModel refreshTokenModel = new RefreshTokenModel();
        refreshTokenModel.setUsername(tokenModel.getUsername());
        refreshTokenModel.setToken(tokenModel.getRefreshToken().getToken());
        refreshTokenModel.setExpDate(tokenModel.getRefreshToken().getExpDate());
        if (refreshTokenService.validateRefreshToken(refreshTokenModel)) {
            final String accessToken = jwtUtility.generateToken(
                    userDetailsService.loadUserByUsername(tokenModel.getUsername()));
            loginDetailsRepository.save(new LoginDetailsModel(tokenModel.getUsername(), accessToken,
                    new Date(System.currentTimeMillis()), jwtUtility.extractExpiration(accessToken)));
            return new RefreshTokenResponseDto(tokenModel.getUsername(), accessToken,
                    modelMapper.map(
                            refreshTokenService.createRefreshToken(tokenModel.getUsername()), RefreshTokenDto.class));
        } else {
            throw new RefreshTokenValidationException("Token has expired or Mismatch found");
        }
    }
}
