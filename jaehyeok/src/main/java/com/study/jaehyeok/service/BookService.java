package com.study.jaehyeok.service;

import com.study.jaehyeok.domain.Book;
import com.study.jaehyeok.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


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
    public Long join(Book book) {
        bookRepository.save(book);
        return book.getId();
    }

    @Transactional
    public void update(Book book) {
        Long id = book.getId();
        Book findBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessError("해당 게시글이 존재하지 않습니다."));

        findBook.update(book);
    }

    @Transactional
    public Long delete(long id) {
        bookRepository.deleteById(id);
        return id;
    }
}
