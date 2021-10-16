package lk.coolbreez.www.model.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "security", name = "otp")
public class OtpModel {

    @Id
    @GeneratedValue
    @Column(name = "email", length = 128)
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "otp", length = 6)
    private String otp;
}
