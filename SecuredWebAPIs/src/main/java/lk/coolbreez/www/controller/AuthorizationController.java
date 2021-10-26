package lk.coolbreez.www.controller;

import lk.coolbreez.www.dto.request.RefreshTokenRequestDto;
import lk.coolbreez.www.dto.response.RefreshTokenResponseDto;
import lk.coolbreez.www.exception.RefreshTokenValidationException;
import lk.coolbreez.www.service.implement.AuthorizationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthorizationController {

    private final AuthorizationServiceImpl authorizationService;

    public AuthorizationController(AuthorizationServiceImpl authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<RefreshTokenResponseDto> refreshAccessToken(
            @RequestBody RefreshTokenRequestDto tokenModel) throws RefreshTokenValidationException {
        return ResponseEntity.ok().body(authorizationService.createNewAccessToken(tokenModel));
    }
}

