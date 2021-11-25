package com.study.jaehyeok.dto;

import com.study.jaehyeok.domain.Book;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResBook {

    private List<Book> bookList;
    private Book book;

    private Long id;
    private String ResCode;
    private String ResMsg;
    private String ResErr;

}
