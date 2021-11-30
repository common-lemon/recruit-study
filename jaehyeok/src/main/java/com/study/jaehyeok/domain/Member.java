package com.study.jaehyeok.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @NotNull
    private String userName;

    @NotNull
    @Column(length = 500)
    private String password;

    @Column(length = 100)
    private String name;
    @Column(length = 30)
    private String deptName;
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public void update(Member member) {
        this.password = member.password;
        this.name = member.name;
        this.deptName = member.deptName;
    }
}
