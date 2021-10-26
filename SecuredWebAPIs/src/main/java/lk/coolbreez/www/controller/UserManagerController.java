package lk.coolbreez.www.controller;

import lk.coolbreez.www.dto.request.admin.CreateUserRequestDto;
import lk.coolbreez.www.dto.request.admin.DeleteUserRequestDto;
import lk.coolbreez.www.dto.request.admin.UpdateAuthorityRequestDto;
import lk.coolbreez.www.dto.request.admin.UpdateUserRequestDto;
import lk.coolbreez.www.dto.response.admin.*;
import lk.coolbreez.www.service.implement.UserManagerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class UserManagerController {

    private final UserManagerServiceImpl userManagerService;

    public UserManagerController(UserManagerServiceImpl userManagerService) {
        this.userManagerService = userManagerService;
    }

    @GetMapping(path = "/{username}/user")
    public ResponseEntity<GetUserResponseDto> getUserInfo(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userManagerService.getUserDetails(username));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        return ResponseEntity.ok().body(userManagerService.createUser(createUserRequestDto));
    }

    @PutMapping(path = "/update/user")
    public ResponseEntity<UpdateUserResponseDto> updateUser(@RequestBody UpdateUserRequestDto updateUserRequestDto) {
        return ResponseEntity.ok().body(userManagerService.updateUser(updateUserRequestDto));
    }

    @PutMapping(path = "/update/authority")
    public ResponseEntity<UpdateAuthorityResponseDto> updateAuthority(
            @RequestBody UpdateAuthorityRequestDto updateAuthorityRequestDto) {
        return ResponseEntity.ok().body(userManagerService.updateAuthority(updateAuthorityRequestDto));
    }

    @DeleteMapping(path = "/delete/user")
    public ResponseEntity<DeleteUserResponseDto> deleteUser(@RequestBody DeleteUserRequestDto deleteUserRequestDto) {
        return ResponseEntity.ok().body(userManagerService.deleteUser(deleteUserRequestDto));
    }
}
