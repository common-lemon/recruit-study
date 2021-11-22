package com.study.book.rest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

}
