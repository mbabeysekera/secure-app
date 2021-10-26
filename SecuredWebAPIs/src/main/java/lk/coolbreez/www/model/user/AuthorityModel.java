package lk.coolbreez.www.model.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "security", name = "authority")
public class AuthorityModel {
    @Id
    @Column(name = "username", length = 128)
    private String username;

    @Column(name = "authorities", length = 512)
    private String authorities;

    @Column(name = "roles",length = 512)
    private String roles;

    public AuthorityModel(String username, String authorities, String roles) {
        this.username = username;
        this.authorities = authorities;
        this.roles = roles;
    }

    public AuthorityModel() {
    }
}
