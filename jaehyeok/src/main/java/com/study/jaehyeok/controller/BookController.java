package com.study.jaehyeok.controller;

import com.study.jaehyeok.domain.Book;
import com.study.jaehyeok.dto.ReqBook;
import com.study.jaehyeok.dto.ResBook;
import com.study.jaehyeok.service.BookService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public CreateBookResponse bookApply(@RequestBody Book reqbook){
        Long id = bookService.join(reqbook);
        return new CreateBookResponse(id);
    }

    //book 수정
    @PutMapping("/api/book")
    public UpdateBookResponse bookUpdate(@RequestBody Book book){
        System.err.println(book.getId());
        bookService.update(book);
        Book findBook = bookService.findOne(book.getId());
        return new UpdateBookResponse(findBook.getId());
    }

    @DeleteMapping("/api/book/{id}")
    public DeleteBookResponse deleteBook (@PathVariable long id){
        Long ids = bookService.delete(id);
        return new DeleteBookResponse(ids);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    @Data
    static class CreateBookResponse{
        private Long id;
        public CreateBookResponse(Long id){
            this.id = id;
        }
    }

    @Data
    static class UpdateBookResponse {
        private Long id;
        public UpdateBookResponse(Long id){
            this.id = id;
        }
    }
    @Data

    static class DeleteBookResponse {
        private Long id;
        public DeleteBookResponse(Long id){ this.id = id; }
    }
}
