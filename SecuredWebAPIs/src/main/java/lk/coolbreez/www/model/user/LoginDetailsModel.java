package lk.coolbreez.www.model.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(schema = "security", name = "login_details")
public class LoginDetailsModel {
    @Id
    @Column(name = "username", length = 128)
    private String username;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "exp_date")
    private Date expDate;

    @Column(name = "access_token", length = 256)
    private String accessToken;


    public LoginDetailsModel() {
    }

    public LoginDetailsModel(String username, String accessToken, Date lastLogin, Date expDate) {
        this.username = username;
        this.lastLogin = lastLogin;
        this.expDate = expDate;
        this.accessToken = accessToken;
    }
}
