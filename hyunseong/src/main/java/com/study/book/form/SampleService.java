package com.study.book.form;

import com.study.book.account.Account;
import com.study.book.account.AccountContext;
import com.study.book.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class SampleService {

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void insert(Model model) {
        // 같은 스레드에서 account를 공유(SecurityContextHolder의 기본 전략)
        Account account = AccountContext.getAccount();
        model.addAttribute("erum", account.getErum());
        model.addAttribute("deptname", account.getDeptname());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void detail(Model model) {
        // 같은 스레드에서 account를 공유(SecurityContextHolder의 기본 전략)
        Account account = AccountContext.getAccount();
        model.addAttribute("role", account.getRole());
        model.addAttribute("erum", account.getErum());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void mypage(Model model) {
        // 같은 스레드에서 account를 공유(SecurityContextHolder의 기본 전략)
        Account account = AccountContext.getAccount();
        model.addAttribute("id", account.getId());
        model.addAttribute("username", account.getUsername());
        model.addAttribute("password", account.getPassword());
        model.addAttribute("deptname", account.getDeptname());
        model.addAttribute("erum", account.getErum());
        model.addAttribute("role", account.getRole());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    }

    /*@Secured({"ROLE_USER", "ROLE_ADMIN"})
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
    }*/

    @Async
    public void asyncService() {
        SecurityLogger.log("Async Service");
        System.out.println("Async service is called.");
    }

}
