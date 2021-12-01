package com.study.jaehyeok.controller;

import com.study.jaehyeok.domain.Member;
import com.study.jaehyeok.dto.ReqMember;
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
        Member findMember = memberService.findOne(id);
        return new Result(findMember);
    }

    //비밀번호 체크
    @PostMapping(value = "/api/passwordcheck")
    public boolean PasswordCheck(@RequestBody Member member){
        String id = member.getUserName();
        String password = member.getPassword();
        Member findMember = memberService.findOne(id);

        String encodedPassword = findMember.getPassword();
        boolean result = false;
        if(passwordEncoder.matches(password, encodedPassword)){
            result = true;
        }else if(!passwordEncoder.matches(password, encodedPassword )){
            result = false;
        }
        return result;
    }

    // 로그인
    @PostMapping(value = "/api/login")
    public ResponseEntity<TokenDto> login(@RequestBody Account account) {

        Authentication authentication = restAuthenticationProvider.authenticate(account);

        String jwt = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer" + jwt);
        return new ResponseEntity<>(new TokenDto(jwt,authentication), httpHeaders ,HttpStatus.OK );
    }

    // 맴버 수정
    @PutMapping(value = "/api/member")
    public ResponseEntity<ResMember> UpdateMember(@RequestBody ReqMember reqMember){

        ResMember resMember = memberService.update(reqMember);
        return new ResponseEntity<>(resMember, HttpStatus.OK);
    }


    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

}
