package lk.coolbreez.www.model.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "security", name = "user")
public class UserModel {
    @Id
    @Column(name = "username", length = 128)
    private String username;

    @Column(name = "mobile", length = 10)
    private String mobile;

    @Column(name = "password", length = 256)
    private String password;

    @Column(name = "enabled")
    private boolean isEnabled;

    public UserModel(String username, String mobile, String password, boolean isEnabled) {
        this.username = username;
        this.mobile = mobile;
        this.password = password;
        this.isEnabled = isEnabled;
    }

    public UserModel() {
    }
}
