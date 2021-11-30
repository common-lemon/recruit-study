package com.study.book.rest.svr.itf;

import com.study.book.rest.dto.ReqUserParams;
import com.study.book.rest.dto.ResAccount;

public interface UserSvrItf {

    /*ResAccount mypage(ReqUserParams reqUserParams);*/
    ResAccount save(ReqUserParams reqUserParams);

}
