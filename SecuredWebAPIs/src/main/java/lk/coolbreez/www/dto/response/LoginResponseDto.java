package lk.coolbreez.www.dto.response;

import lk.coolbreez.www.dto.RefreshTokenDto;
import lombok.Data;

@Data
public class LoginResponseDto {
    private String username;
    private String accessToken;
    private String type;
    private RefreshTokenDto refreshToken;

    public LoginResponseDto(String username, String accessToken, String type, RefreshTokenDto refreshToken) {
        this.username = username;
        this.accessToken = accessToken;
        this.type = type;
        this.refreshToken = refreshToken;
    }
}
