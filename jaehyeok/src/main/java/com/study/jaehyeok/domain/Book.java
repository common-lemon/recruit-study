package com.study.jaehyeok.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30)
    private String bookNo;
    @Column(length = 30)
    private String deptName;
    @Column(length = 255)
    private String title;
    @Column(length = 30)
    private String registerNm;
    @Column(length = 50)
    private String publisher;
    private int bookPrice;
    @Column(length = 255)
    private String regRsn;
    private int count;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date date;

    @Enumerated(EnumType.STRING)
    private BookStatus status;


    public void update(Book book) {
        this.title = book.title;
        this.publisher = book.publisher;
        this.bookPrice = book.bookPrice;
        this.count = book.count;
        this.status = book.status;
    }
}
