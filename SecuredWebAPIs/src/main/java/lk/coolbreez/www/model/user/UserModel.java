package lk.coolbreez.www.model.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "security", name = "user")
public class UserModel {
    @Id
    @GeneratedValue
    @Column(name = "email", length = 128)
    private String email;

    @Column(name = "username", nullable = false, length = 128)
    private String username;

    @Column(name = "mobile", nullable = false, length = 10)
    private String mobile;

    @Column(name = "password", nullable = false, length = 256)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean isEnabled;
}
