package lk.coolbreez.www.dto.response.admin;

import lombok.Data;

@Data
public class CreateUserResponseDto {
    private String username;
    private String details;


    public CreateUserResponseDto(String username, String details) {
        this.username = username;
        this.details = details;
    }
}
