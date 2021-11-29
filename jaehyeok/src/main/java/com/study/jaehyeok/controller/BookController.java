package com.study.jaehyeok.controller;

import com.study.jaehyeok.domain.Book;
import com.study.jaehyeok.dto.ReqBook;
import com.study.jaehyeok.dto.ResBook;
import com.study.jaehyeok.service.BookService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    //book list 조회
    @GetMapping("/api/book")
    public Result bookList (){
        List<Book> findBook = bookService.findBook();
        return new Result(findBook);
    }

    //book 상세보기
    @GetMapping("/api/book/{id}")
    public Result bookDetail(@PathVariable long id){
        Book book = bookService.findOne(id);
        return new Result(book);
    }

    //book 등록
    @PostMapping("/api/book")
    public ResponseEntity<ResBook> bookApply(@RequestBody ReqBook reqbook){
        ResBook resBook = bookService.join(reqbook);
        return new ResponseEntity<>(resBook, HttpStatus.OK);
    }

    //book 수정
    @PutMapping("/api/book")
    public ResponseEntity<ResBook> bookUpdate(@RequestBody ReqBook reqbook){
        ResBook resBook = bookService.update(reqbook);
        return new ResponseEntity<>(resBook, HttpStatus.OK);
    }
    //book 삭제
    @DeleteMapping("/api/book/{id}")
    public ResponseEntity<ResBook> deleteBook (@PathVariable long id){
        ResBook resBook = bookService.delete(id);
        return new ResponseEntity<>(resBook, HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

}
