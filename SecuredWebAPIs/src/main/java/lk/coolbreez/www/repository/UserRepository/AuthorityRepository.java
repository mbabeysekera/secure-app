package lk.coolbreez.www.repository.UserRepository;

import lk.coolbreez.www.model.user.AuthorityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityModel, String> {
}
