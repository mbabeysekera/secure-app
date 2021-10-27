package lk.coolbreez.www.dto.response;

import lombok.Data;

@Data
public class ErrorResponseDto {
    private String details;
    private String status;

    public ErrorResponseDto(String details, String status) {
        this.details = details;
        this.status = status;
    }
}
