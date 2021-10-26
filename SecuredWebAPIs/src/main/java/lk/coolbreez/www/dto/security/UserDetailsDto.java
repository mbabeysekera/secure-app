package lk.coolbreez.www.dto.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsDto implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
    private boolean isActive;

    public UserDetailsDto() {

    }

    public UserDetailsDto(String username, String password, String authorities, boolean isActive) {
        this.username = username;
        this.password = password;
        this.authorities = Arrays.stream(authorities.split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
