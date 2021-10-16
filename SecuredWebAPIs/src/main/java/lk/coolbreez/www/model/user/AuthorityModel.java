package lk.coolbreez.www.model.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "security", name = "authority")
public class AuthorityModel {
    @Id
    @Column(name = "email", nullable = false, length = 128)
    @GeneratedValue
    private String email;

    @Column(name = "authorities", length = 512)
    private String authorities;

    @Column(name = "roles",length = 512)
    private String roles;
}
