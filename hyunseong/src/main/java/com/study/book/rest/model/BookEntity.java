package com.study.book.rest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30)
    private String bookNo;
    private int bookPrice;
    private int count;
    @Column(length = 30)
    private String deptName;
    @Column(length = 50)
    private String publisher;
    @Column(length = 255)
    private String regRsn;
    @Column(length = 30)
    private String registerNm;
    @Column(length = 255)
    private String title;
    @Column(length = 1)
    private String isBook;

}
