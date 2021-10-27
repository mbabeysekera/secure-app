package lk.coolbreez.www.repository.UserRepository;

import lk.coolbreez.www.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {

}
