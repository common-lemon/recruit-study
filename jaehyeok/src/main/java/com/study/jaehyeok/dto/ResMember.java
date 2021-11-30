package com.study.jaehyeok.dto;


import com.study.jaehyeok.domain.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;

import java.util.List;

@Getter
@Setter
public class ResMember {

    private List<Member> memberList;
    private Member member;

    private Long id;
    private String ResCode;
    private String ResMsg;
    private String ResErr;


}
