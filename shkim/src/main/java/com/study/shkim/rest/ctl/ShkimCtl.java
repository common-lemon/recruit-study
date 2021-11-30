package com.study.shkim.rest.ctl;

import com.study.shkim.rest.dto.ReqBookParams;
import com.study.shkim.rest.dto.ResBook;
import com.study.shkim.rest.svr.itf.BookSvrItf;
import com.study.shkim.security.account.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/book")
public class ShkimCtl {

    private final BookSvrItf bookSvr;
    private final AccountService accountSvr;

    @Autowired
    public ShkimCtl(BookSvrItf bookSvr, AccountService accountSvr) {
        this.bookSvr = bookSvr;
        this.accountSvr = accountSvr;
    }

    // 리스트로 이동
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView goList(ReqBookParams params, HttpServletResponse response){
        log.info("[정보] params : {}", params.toString());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index.html");
        response.setStatus(200);
        mav.addObject("books", this.bookSvr.list(params));
        return mav;
    }

    // 등록으로 이동
    @RequestMapping(value = "/save", method=RequestMethod.GET)
    public ModelAndView goRegForm(Principal principal){
        log.info("[정보] principal : {}", principal.toString());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("form.html");
        mav.addObject("login", this.accountSvr.getAccount(principal.getName()));
        return mav;
    }

    // 상세로 이동
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView goDetail(ReqBookParams params, Principal principal){
        log.info("[정보] params : {}", params.toString());
        log.info("[정보] principal : {}", principal.toString());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("form.html");
        mav.addObject("book", this.bookSvr.detail(params));
        mav.addObject("login", this.accountSvr.getAccount(principal.getName()));
        return mav;
    }

    // 등록, 수정
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ResBook> save(@RequestBody ReqBookParams params){
        log.info("[정보] params : {}", params.toString());
        return new ResponseEntity<>(this.bookSvr.save(params), HttpStatus.OK);
    }

    // 삭제
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ResponseEntity<ResBook> delete(ReqBookParams params){
        log.info("[정보] params : {}", params.toString());
        return new ResponseEntity<>(this.bookSvr.delete(params), HttpStatus.OK);
    }

}
