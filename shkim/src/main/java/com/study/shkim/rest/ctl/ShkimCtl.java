package com.study.shkim.rest.ctl;

import com.study.shkim.rest.dto.ReqBookParams;
import com.study.shkim.rest.dto.ResBook;
import com.study.shkim.rest.svr.itf.BookSvrItf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/book")
public class ShkimCtl {

    private final BookSvrItf bookSvr;

    @Autowired
    public ShkimCtl(BookSvrItf bookSvr) {
        this.bookSvr = bookSvr;
    }

/*    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<ResBook> list(ReqBookParams params){
        log.info("[정보] params : {}", params.toString());
        return new ResponseEntity<>(this.bookSvr.list(params), HttpStatus.OK);
    }*/

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ReqBookParams params, HttpServletResponse response){
        log.info("[정보] params : {}", params.toString());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index.html");
        response.setStatus(200);
        mav.addObject("books", this.bookSvr.list(params));
        return mav;
    }

    @RequestMapping(value = "/save", method=RequestMethod.GET)
    public String goRegForm(){
        return "form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ResBook> save(@RequestBody ReqBookParams params){
        log.info("[정보] params : {}", params.toString());
        return new ResponseEntity<>(this.bookSvr.save(params), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView detail(ReqBookParams params, HttpServletResponse response){
        log.info("[정보] params : {}", params.toString());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("form.html");
        response.setStatus(200);
        mav.addObject("book", this.bookSvr.detail(params));
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResBook> delete(ReqBookParams params){
        log.info("[정보] params : {}", params.toString());
        return new ResponseEntity<>(this.bookSvr.delete(params), HttpStatus.OK);
    }


}
