package com.study.book.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReqUserParams {

    private Integer id;
    private String username;
    private String password;
    private String role;
    private String deptname;
    private String erum;

}
