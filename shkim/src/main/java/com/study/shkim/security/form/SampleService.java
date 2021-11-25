package com.study.shkim.security.form;

import com.study.shkim.security.account.Account;
import com.study.shkim.security.account.AccountContext;
import com.study.shkim.security.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void dashboard() {
        // 같은 스레드에서 account를 공유(SecurityContextHolder의 기본 전략)
        Account account = AccountContext.getAccount();
        System.out.println("======================");
        System.out.println(account.getUsername());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("======================");
        System.out.println(authentication);
        System.out.println(userDetails.getUsername());
    }

    @Async
    public void asyncService() {
        SecurityLogger.log("Async Service");
        System.out.println("Async service is called.");
    }

}
