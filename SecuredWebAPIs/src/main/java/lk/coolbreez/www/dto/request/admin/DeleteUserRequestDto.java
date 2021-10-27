package lk.coolbreez.www.dto.request.admin;

import lombok.Data;

@Data
public class DeleteUserRequestDto {
    private String adminName;
    private String adminPassword;
    private String username;

    public DeleteUserRequestDto(String adminName, String adminPassword, String username) {
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.username = username;
    }
}
