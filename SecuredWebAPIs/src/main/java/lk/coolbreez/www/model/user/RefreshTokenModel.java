package lk.coolbreez.www.model.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(schema = "security", name = "refresh_token")
public class RefreshTokenModel {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "token")
    private String token;

    @Column(name = "exp_date")
    private Date expDate;

    public RefreshTokenModel() {
    }

    public RefreshTokenModel(String username, String token, Date expDate) {
        this.username = username;
        this.token = token;
        this.expDate = expDate;
    }
}
