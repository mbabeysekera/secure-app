package lk.coolbreez.www.repository.UserRepository;

import lk.coolbreez.www.model.user.RefreshTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenModel, String> {
}
