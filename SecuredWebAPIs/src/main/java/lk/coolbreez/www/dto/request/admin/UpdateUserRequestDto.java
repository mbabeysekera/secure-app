package lk.coolbreez.www.dto.request.admin;

import lombok.Data;

@Data
public class UpdateUserRequestDto {
    private String adminName;
    private String adminPassword;
    private String username;
    private String userPassword;
    private String mobile;
    private boolean isActivated;

    public UpdateUserRequestDto(String adminName, String adminPassword, String username, String userPassword,
                                String mobile, boolean isActivated) {
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.username = username;
        this.userPassword = userPassword;
        this.mobile = mobile;
        this.isActivated = isActivated;
    }
}
