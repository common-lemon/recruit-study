package com.study.jaehyeok.jwt;

import com.study.jaehyeok.domain.Authority;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@NoArgsConstructor
public class Account implements Authentication {

    private String userName;
    private String password;
    private Authority authority;
    private boolean authenticated;

    public Account(String userName,
                   Authority authority
                    ) {

        this.userName = userName;
        this.authority = authority;

        this.authenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.userName;
    }


    @Override
    public String getName() {
        return this.userName;
    }
}
