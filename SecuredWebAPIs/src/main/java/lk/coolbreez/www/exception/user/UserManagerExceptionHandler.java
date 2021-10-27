package lk.coolbreez.www.exception.user;

import lk.coolbreez.www.dto.response.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class UserManagerExceptionHandler {

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> userDoesNotExists(UsernameNotFoundException e) {
        log.error(e.getLocalizedMessage());
        return ResponseEntity.badRequest().body(new ErrorResponseDto(e.getMessage(), "error"));
    }
}
