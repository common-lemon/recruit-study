package com.study.book.rest.dto;

import com.study.book.account.Account;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ResAccount {

    private List<Account> UserList;
    private Account userEntity;

    private String ResCode;
    private String ResMsg;
    private String ResErr;

}
