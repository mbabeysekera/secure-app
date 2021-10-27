package lk.coolbreez.www.exception.user;

import lk.coolbreez.www.dto.response.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class LoginExceptionHandler {

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> badCredentialExceptionResponse(BadCredentialsException e) {
        log.error(e.getLocalizedMessage());
        return ResponseEntity.badRequest().body(new ErrorResponseDto(e.getMessage(), "error"));
    }
}
