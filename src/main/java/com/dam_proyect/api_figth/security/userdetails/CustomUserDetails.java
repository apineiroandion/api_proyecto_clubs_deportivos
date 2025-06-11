package com.dam_proyect.api_figth.security.userdetails;

import com.dam_proyect.api_figth.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public record CustomUserDetails(User user) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aquí puedes devolver roles si tienes: ej. List.of(new SimpleGrantedAuthority("ROLE_USER"))
        return Collections.emptyList();
    }

    public String getId() {
        return user.getId();  // Aquí devuelves el ID real
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // o getUsername(), según cómo lo gestiones
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

}

