package com.study.book.form;

import com.study.book.account.AccountContext;
import com.study.book.account.AccountRepository;
import com.study.book.common.SecurityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@Controller
@EnableAsync
public class SampleController {

    @Autowired
    SampleService sampleService;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("pageName", "index");
        if (principal == null) {
            model.addAttribute("message", "Hello Spring Security");
        } else {
            model.addAttribute("message", "Hello " + principal.getName() + ", Spring Security");
        }
        return "index";
    }

    @GetMapping("/test")
    public String test(Model model, Principal principal) {
        return "test";
    }

    @GetMapping("/book/insert")
    public String bookInsert(Model model, Principal principal) {
        AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
        sampleService.insert(model);

        return "insert";
    }

    @RequestMapping("/book/detail/{id}")
    public String bookDetail(Model model, Principal principal) {
        AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
        sampleService.detail(model);

        return "detail";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("pageName", "info");
        model.addAttribute("message", "info");

        return "info";
    }



    /*@GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("pageName", "dashboard");
        model.addAttribute("message", "Hello " + principal.getName());
        AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
        sampleService.dashboard();

        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("pageName", "admin");
        model.addAttribute("message", "Hello Admin, " + principal.getName());

        return "admin";
    }*/

    @GetMapping("/async-handler")
    @ResponseBody
    public Callable<String> asyncHandler() {
        SecurityLogger.log("MVC");
        return () -> {
            SecurityLogger.log("Callable");
            return "Async Handler";
        };
    }

    @GetMapping("/async-service")
    @ResponseBody
    public String asyncService() {
        SecurityLogger.log("MVC, before async service");
        sampleService.asyncService();
        SecurityLogger.log("MVC, after async service");
        return "Async Service";
    }
}
