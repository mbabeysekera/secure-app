package lk.coolbreez.www.dto.response.admin;

import lombok.Data;

import java.util.Date;

@Data
public class GetUserResponseDto {
    private String mobile;
    private String authorities;
    private String roles;
    private Date lastLogin;
    private boolean status;

    public GetUserResponseDto(String mobile, boolean status, String authorities, String roles, Date lastLogin) {
        this.mobile = mobile;
        this.authorities = authorities;
        this.roles = roles;
        this.lastLogin = lastLogin;
        this.status = status;
    }
}
