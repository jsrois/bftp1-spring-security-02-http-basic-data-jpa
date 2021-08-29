package org.factoriaf5.app.configuration;

import org.factoriaf5.app.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final GrantedAuthority authority;

    public CustomUserDetails(String username, String password, GrantedAuthority authority) {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public static CustomUserDetails buildFrom(User user) {
        return new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                new SimpleGrantedAuthority(user.getRole())
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authority);
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
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomUserDetails that = (CustomUserDetails) o;
        return username.equals(that.username) && password.equals(that.password) && authority.equals(that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, authority);
    }
}
