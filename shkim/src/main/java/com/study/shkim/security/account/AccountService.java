package com.study.shkim.security.account;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {

    com.study.shkim.security.account.AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("===========loadUserByUsername==========");
        Account account = accountRepository.findByUsername(username);
        if(account == null) {
            throw new UsernameNotFoundException(username);
        }

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }

    public Account createNew(Account account) {
        account.encodePassword(passwordEncoder);
        return accountRepository.save(account);
    }

    public Map<String, String> getAccount(String username) {
        log.info("[정보] username : {}", username);
        Map<String, String> map = new HashMap<>();
        Account account = accountRepository.findByUsername(username);
        map.put("deptName", account.getDeptName());
        map.put("registerNm", account.getRegisterNm());
        map.put("role", account.getRole());

        return map;
    }

}
