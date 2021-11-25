package com.study.book.rest.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestCtl {

    @RequestMapping("/")
    public String index() {
        return "index2";
    }

    @RequestMapping("/book/insert")
    public String insert() {
        return "insert";
    }

    @RequestMapping("/book/detail/{id}")
    public String detail(@PathVariable("id") String id) {
        return "detail";
    }

    /*@RequestMapping("/book/test/{id}")
    public String detail(@PathVariable Long id, Model model) {

        return
    }*/

}
