package lk.coolbreez.www.dto.request;

import lk.coolbreez.www.dto.RefreshTokenDto;
import lombok.Data;

@Data
public class RefreshTokenRequestDto {
    private String username;
    private RefreshTokenDto refreshToken;

    public RefreshTokenRequestDto() {
    }

    public RefreshTokenRequestDto(String username, RefreshTokenDto refreshToken) {
        this.username = username;
        this.refreshToken = refreshToken;
    }
}
