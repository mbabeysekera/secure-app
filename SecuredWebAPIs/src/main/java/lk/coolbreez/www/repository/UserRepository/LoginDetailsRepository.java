package lk.coolbreez.www.repository.UserRepository;

import lk.coolbreez.www.model.user.LoginDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDetailsRepository extends JpaRepository<LoginDetailsModel, String> {
}
