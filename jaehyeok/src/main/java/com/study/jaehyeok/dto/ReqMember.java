package com.study.jaehyeok.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.jaehyeok.domain.Authority;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqMember {

    private Long id;
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String name;
    private String deptName;
    private Authority authority;

}
