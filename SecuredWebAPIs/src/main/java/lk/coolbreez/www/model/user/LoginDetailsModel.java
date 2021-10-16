package lk.coolbreez.www.model.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(schema = "security", name = "login_details")
public class LoginDetailsModel {
    @Id
    @Column(name = "email", length = 128)
    @GeneratedValue
    private String email;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "exp_date")
    private Date expDate;

    @Column(name = "access_token", length = 256)
    private String accessToken;
}
