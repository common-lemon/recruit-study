package com.study.jaehyeok.controller;

import com.study.jaehyeok.domain.Member;
import com.study.jaehyeok.dto.TokenDto;
import com.study.jaehyeok.dto.ResMember;
import com.study.jaehyeok.jwt.Account;
import com.study.jaehyeok.jwt.JwtFilter;
import com.study.jaehyeok.jwt.RestAuthenticationProvider;
import com.study.jaehyeok.jwt.TokenProvider;
import com.study.jaehyeok.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class MemberController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    private final RestAuthenticationProvider restAuthenticationProvider;

    //회원가입
    @PostMapping("/api/auth/member")
    public ResponseEntity<ResMember> SignUp(@RequestBody Member member){
        ResMember resMember = memberService.join(member);
        return new ResponseEntity<>(resMember, HttpStatus.OK);
    }
    // 회원 중복체크
    @GetMapping(value = "/api/membercheck/{id}")
    public Result findOne(@PathVariable("id") String id){
        System.err.println(id);
        Member findMember = memberService.findOne(id);
        return new Result(findMember);
    }

    // 로그인
    @PostMapping(value = "/api/login")
    public ResponseEntity<TokenDto> login(@RequestBody Account account) {

        Authentication authentication = restAuthenticationProvider.authenticate(account);

        String jwt = tokenProvider.createToken(authentication);
        System.err.println("jwt==>"+jwt);
        System.err.println("authentication==>"+authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer" + jwt);
        return new ResponseEntity<>(new TokenDto(jwt,authentication), httpHeaders ,HttpStatus.OK );
    }



    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

}
