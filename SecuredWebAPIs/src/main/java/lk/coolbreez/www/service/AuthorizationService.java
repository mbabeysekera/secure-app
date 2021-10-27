package lk.coolbreez.www.service;

import lk.coolbreez.www.dto.request.RefreshTokenRequestDto;
import lk.coolbreez.www.dto.response.RefreshTokenResponseDto;
import lk.coolbreez.www.exception.RefreshTokenValidationException;

public interface AuthorizationService {
    RefreshTokenResponseDto createNewAccessToken(RefreshTokenRequestDto tokenModel) throws RefreshTokenValidationException;
}
