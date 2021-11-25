package com.study.book.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReqBookParams {

    private Long id;
    private String bookNo;
    private int bookPrice;
    private int count;
    private String deptName;
    private String publisher;
    private String regRsn;
    private String registerNm;
    private String title;
    private String isBook;

}
