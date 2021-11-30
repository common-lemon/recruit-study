package com.study.book.rest.ctl;

import com.study.book.rest.dto.ReqUserParams;
import com.study.book.rest.dto.ResAccount;
import com.study.book.rest.svr.itf.UserSvrItf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("rest/user")
public class UserCtl {

    private final UserSvrItf userSvr;

    @Autowired
    public UserCtl(UserSvrItf userSvr) {
        this.userSvr = userSvr;
    }

    /*@RequestMapping(value = "/mypage", method = RequestMethod.GET)
    public ResponseEntity<ResAccount> mypage(ReqUserParams params) {
        log.info("[정보] params : {}", params.toString());
        return new ResponseEntity<>(this.userSvr.mypage(params), HttpStatus.OK);
    }*/

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ResAccount> save(@RequestBody ReqUserParams params) {
        System.err.println(params);
        log.info("[정보] params : {}", params.toString());
        return new ResponseEntity<>(this.userSvr.save(params), HttpStatus.OK);
    }

}
