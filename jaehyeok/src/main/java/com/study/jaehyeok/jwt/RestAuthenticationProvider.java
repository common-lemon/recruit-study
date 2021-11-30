package com.study.jaehyeok.jwt;

import com.study.jaehyeok.domain.Member;
import com.study.jaehyeok.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RestAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = (String) authentication.getPrincipal();
        final String password = (String) authentication.getCredentials();

        final Member findMember = memberRepository.findByUserName(username);
        if (!passwordEncoder.matches(password, findMember.getPassword())) {
            throw new BadCredentialsException("비밀번호가 틀렸습니다.");
        }


        //  로그인 처리
        final Account account = new Account(
                findMember.getUserName(),
                findMember.getAuthority()
        );
        SecurityContextHolder.getContext().setAuthentication(account);
        System.err.println(account);
        return account;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(Account.class);
    }
}
