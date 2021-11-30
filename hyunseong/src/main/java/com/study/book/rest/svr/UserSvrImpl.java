package com.study.book.rest.svr;

import com.study.book.account.Account;
import com.study.book.account.AccountRepository;
import com.study.book.rest.dto.ReqUserParams;
import com.study.book.rest.dto.ResAccount;
import com.study.book.rest.svr.itf.UserSvrItf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("userSvr")
public class UserSvrImpl implements UserSvrItf {

    private AccountRepository accountRepository;

    @Autowired
    public UserSvrImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /*@Override
    public ResAccount mypage(ReqUserParams reqUserParams) {

        ResAccount resAccount = new ResAccount();
        resAccount.setResCode("S-001");
        resAccount.setResMsg("정상 처리 되었습니다.");

        resAccount.setUserEntity(this.accountRepository.findById(reqUserParams.getId()).orElse(null));

        return resAccount;
    }*/

    @Override
    public ResAccount save(ReqUserParams reqUserParams) {

        ResAccount resAccount = new ResAccount();
        resAccount.setResCode("S-001");
        resAccount.setResMsg("정상 처리 되었습니다.");

        Account account = new Account();
        BeanUtils.copyProperties(reqUserParams, account);
        try {
            resAccount.setUserEntity(this.accountRepository.save(account));
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
            resAccount.setResErr(e.getMessage());
            resAccount.setResCode("F-001");
            resAccount.setResMsg("요청하신 작업이 실패하였습니다.");
        }

        return resAccount;
    }

}
