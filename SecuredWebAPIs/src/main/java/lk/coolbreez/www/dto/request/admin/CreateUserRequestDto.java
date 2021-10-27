package lk.coolbreez.www.dto.request.admin;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreateUserRequestDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String mobile;
    @NonNull
    private boolean isActivated;
    @NonNull
    private String authorities;
    @NonNull
    private String roles;

    public CreateUserRequestDto(@NonNull String username, @NonNull String password, @NonNull String mobile,
                                @NonNull boolean isActivated, @NonNull String authorities, @NonNull String roles) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.isActivated = isActivated;
        this.authorities = authorities;
        this.roles = roles;
    }
}