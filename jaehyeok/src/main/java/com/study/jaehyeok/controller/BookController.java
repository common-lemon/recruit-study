package com.study.jaehyeok.controller;

import com.study.jaehyeok.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/api/book")
    public String bookApply(){
        String result = "result";
        System.err.println("서버도착");
        return result;
    }

}
