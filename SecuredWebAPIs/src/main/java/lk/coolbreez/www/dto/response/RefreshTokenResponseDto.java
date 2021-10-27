package lk.coolbreez.www.dto.response;

import lk.coolbreez.www.dto.RefreshTokenDto;
import lk.coolbreez.www.model.user.RefreshTokenModel;
import lombok.Data;

@Data
public class RefreshTokenResponseDto {
    private String username;
    private String accessToken;
    private RefreshTokenDto refreshToken;

    public RefreshTokenResponseDto() {
    }

    public RefreshTokenResponseDto(String username, String accessToken, RefreshTokenDto refreshToken) {
        this.username = username;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
