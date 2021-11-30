package com.study.jaehyeok.dto;

import com.study.jaehyeok.domain.Authority;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
public class ReqMember {

    private String userName;
    private String password;
    private String name;
    private String deptName;
    private Authority authority;

}
