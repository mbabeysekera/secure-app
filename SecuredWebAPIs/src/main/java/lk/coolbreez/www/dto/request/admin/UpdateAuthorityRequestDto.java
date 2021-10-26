package lk.coolbreez.www.dto.request.admin;

import lombok.Data;

@Data
public class UpdateAuthorityRequestDto {
    private String adminName;
    private String username;
    private String roles;
    private String authorities;

    public UpdateAuthorityRequestDto(String adminName, String username, String roles, String authorities) {
        this.adminName = adminName;
        this.username = username;
        this.roles = roles;
        this.authorities = authorities;
    }
}
