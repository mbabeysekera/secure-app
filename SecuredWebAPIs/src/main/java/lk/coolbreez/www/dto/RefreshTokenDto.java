package lk.coolbreez.www.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RefreshTokenDto {
    private String token;
    private Date expDate;

    public RefreshTokenDto() {
    }

    public RefreshTokenDto(String token, Date expDate) {
        this.token = token;
        this.expDate = expDate;
    }
}
