package com.study.jaehyeok.service;

import com.study.jaehyeok.domain.Authority;
import com.study.jaehyeok.domain.Member;
import com.study.jaehyeok.dto.ReqMember;
import com.study.jaehyeok.dto.ResBook;
import com.study.jaehyeok.dto.ResMember;
import com.study.jaehyeok.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    //회원가입
    @Transactional
    public ResMember join(Member reqMember) {
        Member member = new Member();
        ResMember resMember = new ResMember();
        reqMember.setAuthority(Authority.ROLE_USER);
        reqMember.setPassword(reqMember.getPassword());
        BeanUtils.copyProperties(reqMember, member);
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        try {
            resMember.setMember(memberRepository.save(member));
            resMember.setResMsg("회원가입이 되었습니다.");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            resMember.setResErr(e.getMessage());
            resMember.setResMsg("요청하신 작업이 실패하였습니다.");
        }
        return resMember;
    }

    public Member findOne(String id) {
        return memberRepository.findByUserName(id);
    }

    @Transactional
    public ResMember update(ReqMember reqMember) {
        Member member = new Member();
        ResMember resMember = new ResMember();
        reqMember.setPassword(passwordEncoder.encode(reqMember.getPassword()));

        BeanUtils.copyProperties(reqMember, member);
        String id = reqMember.getUserName();
        Member findMember = memberRepository.findByUserName(id);
        try {
            findMember.update(member);
            resMember.setResMsg("회원정보가 수정되었습니다.");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            resMember.setResErr(e.getMessage());
            resMember.setResMsg("요청하신 작업이 실패하였습니다.");
        }
        return resMember;
    }
}
