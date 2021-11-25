package com.study.jaehyeok.service;

import com.study.jaehyeok.domain.Book;
import com.study.jaehyeok.dto.ReqBook;
import com.study.jaehyeok.dto.ResBook;
import com.study.jaehyeok.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findBook(){
        return bookRepository.findAllByOrderByDateDesc();
    };

    public Book findOne(long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessError("해당 도서가 존재하지 않습니다."));
        return book;
    }

    @Transactional
    public ResBook join(ReqBook reqbook) {
        Book book = new Book();
        ResBook resBook = new ResBook();

        BeanUtils.copyProperties(reqbook, book);
        try {
            resBook.setBook(bookRepository.save(book));
            resBook.setResMsg("도서가 신청되었습니다.");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            resBook.setResErr(e.getMessage());
            resBook.setResMsg("요청하신 작업이 실패하였습니다.");
        }
        return resBook;
    }

    @Transactional
    public ResBook update(ReqBook reqbook) {
        Book book = new Book();
        ResBook resBook = new ResBook();
        BeanUtils.copyProperties(reqbook, book);
        Long id = reqbook.getId();
        Book findBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessError("해당 도서가 존재하지 않습니다."));
        try {
            findBook.update(book);
            resBook.setResMsg("도서정보가 수정되었습니다.");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            resBook.setResErr(e.getMessage());
            resBook.setResMsg("요청하신 작업이 실패하였습니다.");
        }

        return resBook;
    }

    @Transactional
    public ResBook delete(long id) {
        ResBook resBook = new ResBook();
        try {
            bookRepository.deleteById(id);
            resBook.setResMsg("도서가 삭제되었습니다.");
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
            resBook.setResErr(e.getMessage());
            resBook.setResMsg("요청하신 작업이 실패하였습니다.");
        }

        return resBook;
    }
}
