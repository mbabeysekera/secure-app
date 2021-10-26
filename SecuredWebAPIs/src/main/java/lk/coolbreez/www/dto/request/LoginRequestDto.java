package lk.coolbreez.www.dto.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class LoginRequestDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
