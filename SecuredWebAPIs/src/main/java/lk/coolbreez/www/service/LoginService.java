package lk.coolbreez.www.service;

import lk.coolbreez.www.dto.request.LoginRequestDto;
import lk.coolbreez.www.dto.response.LoginResponseDto;

public interface LoginService {
    LoginResponseDto authenticateUser(LoginRequestDto loginRequestDto);
}
