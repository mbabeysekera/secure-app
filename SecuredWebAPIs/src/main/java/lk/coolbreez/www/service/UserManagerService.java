package lk.coolbreez.www.service;

import lk.coolbreez.www.dto.request.admin.CreateUserRequestDto;
import lk.coolbreez.www.dto.request.admin.DeleteUserRequestDto;
import lk.coolbreez.www.dto.request.admin.UpdateAuthorityRequestDto;
import lk.coolbreez.www.dto.request.admin.UpdateUserRequestDto;
import lk.coolbreez.www.dto.response.admin.*;

public interface UserManagerService {
    GetUserResponseDto getUserDetails(String username) throws Exception;

    CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto);

    UpdateUserResponseDto updateUser(UpdateUserRequestDto updateUserRequestDto);

    UpdateAuthorityResponseDto updateAuthority(UpdateAuthorityRequestDto updateAuthorityRequestDto);

    DeleteUserResponseDto deleteUser(DeleteUserRequestDto deleteUserRequestDto);
}
