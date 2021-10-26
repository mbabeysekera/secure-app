package lk.coolbreez.www.service.implement;

import lk.coolbreez.www.model.user.RefreshTokenModel;
import lk.coolbreez.www.repository.UserRepository.RefreshTokenRepository;
import lk.coolbreez.www.repository.UserRepository.UserRepository;
import lk.coolbreez.www.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Value("${jwt.refresh-token.expiration}")
    private long refreshTokenExp;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }


    @Override
    public RefreshTokenModel createRefreshToken(String username) {
        RefreshTokenModel refreshTokenModel = new RefreshTokenModel();
        refreshTokenModel.setUsername(userRepository.findById(username).get().getUsername());
        refreshTokenModel.setToken(UUID.randomUUID().toString() + "|" +
                Base64.getEncoder().encodeToString(username.getBytes()));
        refreshTokenModel.setExpDate(new Date(System.currentTimeMillis() + refreshTokenExp));
        refreshTokenRepository.save(refreshTokenModel);
        return refreshTokenModel;
    }

    @Override
    public String getRefreshToken(String username) {
        return refreshTokenRepository.findById(username).get().getToken();
    }

    @Override
    public boolean validateRefreshToken(RefreshTokenModel tokenModel) {
        Optional<RefreshTokenModel> refreshTokenModel = refreshTokenRepository.findById(tokenModel.getUsername());
        if (tokenModel.getExpDate().compareTo(new Date(System.currentTimeMillis())) > 0 &&
                refreshTokenModel.isPresent() && refreshTokenModel.get().getToken().equals(tokenModel.getToken())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteRefreshToken(String username) {
        refreshTokenRepository.deleteById(username);
    }
}
