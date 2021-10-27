package lk.coolbreez.www.dto.response.admin;

import lombok.Data;

@Data
public class DeleteUserResponseDto {
    private String username;
    private String details;

    public DeleteUserResponseDto(String username, String details) {
        this.username = username;
        this.details = details;
    }
}
