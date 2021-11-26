package com.study.book.account;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
@AllArgsConstructor
public class SignUpController {

    AccountService accountService;

    @GetMapping
    public String signupForm(Model model) {
        model.addAttribute("account", new Account());
        return "signup";
    }

    @PostMapping
    public String processSignup(@ModelAttribute Account account) {
        account.setRole(account.getRole());
        accountService.createNew(account);
        System.err.println(account.getId());
        System.err.println(account.getUsername());
        System.err.println(account.getPassword());
        System.err.println(account.getRole());
        System.err.println(account.getDeptname());
        System.err.println(account.getErum());
        return "redirect:/";
    }
}
