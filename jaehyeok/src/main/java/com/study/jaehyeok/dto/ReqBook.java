package com.study.jaehyeok.dto;

import com.study.jaehyeok.domain.BookStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter @Setter
@ToString
public class ReqBook {

    private Long id;
    private String bookNo;
    private String deptName;
    private String title;
    private String registerNm;
    private String publisher;
    private int bookPrice;
    private String regRsn;
    private int count;
    private Date date;
    private BookStatus status;
}
