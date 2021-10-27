package lk.coolbreez.www.exception;

import io.jsonwebtoken.ExpiredJwtException;
import lk.coolbreez.www.dto.response.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    static final String ERROR = "error";

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> illegalArgument(IllegalArgumentException e) {
        log.error(e.getLocalizedMessage());
        return ResponseEntity.badRequest().body(new ErrorResponseDto(e.getMessage(), ERROR));
    }

    @ExceptionHandler(value = RefreshTokenValidationException.class)
    public ResponseEntity<ErrorResponseDto> refreshTokenValidation(RefreshTokenValidationException e) {
        log.error(e.getLocalizedMessage());
        return ResponseEntity.badRequest().body(new ErrorResponseDto(e.getMessage(), ERROR));
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> usernameNotFound(UsernameNotFoundException e) {
        log.error(e.getLocalizedMessage());
        return ResponseEntity.badRequest().body(new ErrorResponseDto(e.getMessage(), ERROR));
    }
}
