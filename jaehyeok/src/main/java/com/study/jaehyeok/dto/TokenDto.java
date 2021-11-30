package com.study.jaehyeok.dto;


import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String token;
    private Authentication authentication;

}