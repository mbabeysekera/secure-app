package lk.coolbreez.www.dto.response.admin;

import lombok.Data;

@Data
public class UpdateUserResponseDto {
    private String username;
    private String details;

    public UpdateUserResponseDto(String username, String details) {
        this.username = username;
        this.details = details;
    }
}
