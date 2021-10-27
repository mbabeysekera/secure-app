package lk.coolbreez.www.controller;

import lk.coolbreez.www.dto.request.LoginRequestDto;
import lk.coolbreez.www.dto.response.LoginResponseDto;
import lk.coolbreez.www.service.implement.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/login")
public class LoginController {
    private final LoginServiceImpl loginService;

    public LoginController(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("User login request: " + loginRequestDto.getUsername());
        return ResponseEntity.ok().body(loginService.authenticateUser(loginRequestDto));
    }

}
