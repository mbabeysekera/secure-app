package lk.coolbreez.www.dto.response.admin;

import lombok.Data;

@Data
public class UpdateAuthorityResponseDto {
    private String username;
    private String details;

    public UpdateAuthorityResponseDto(String username, String details) {
        this.username = username;
        this.details = details;
    }
}
