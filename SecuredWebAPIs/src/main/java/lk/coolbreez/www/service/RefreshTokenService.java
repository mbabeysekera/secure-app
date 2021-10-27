package lk.coolbreez.www.service;

import lk.coolbreez.www.model.user.RefreshTokenModel;

public interface RefreshTokenService {
    RefreshTokenModel createRefreshToken(String username);

    String getRefreshToken(String username);

    boolean validateRefreshToken(RefreshTokenModel tokenModel);

    void deleteRefreshToken(String username);
}
