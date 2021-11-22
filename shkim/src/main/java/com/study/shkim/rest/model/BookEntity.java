package com.study.shkim.rest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
// JPA가 관리하는 클래스, JPA를 사용하여 테이블과 매핑해야할 클래스는 반드시 @Entity를 선언해야 한다
// JPA에서 사용할 엔티티 이름을 지정한다(기본값: 클래스 이름)
@Entity
// 엔티티와 매핑할 테이블을 지정, 매핑할 테이블 이름을 지정한다(기본값: 엔티티 이름)
@Table(name = "books")
public class BookEntity {

    // 기본키를 지정한다
    @Id
    // 기본키를 생성하는 방법을 지정한다. @Id 와 같이 선언된다
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Column을 매핑한다
    @Column(length = 30)
    private String bookNo;
    @Column(length = 30)
    private String deptName;
    @Column(length = 255)
    private String title;
    @Column(length = 30)
    private String registerNm;
    @Column(length = 50)
    private String publisher;
    private int bookPrice;
    @Column(length = 255)
    private String regRsn;
    private int count;

}

/*
@GeneratedValue 속성 : strategy(GenerationType, Optional)
    기본키 생성 전략
    - GenerationType.AUTO: 방언에 따라 나머지 세 가지 전략을 자동으로 지정한다.(기본값)
    - GenerationType.IDENTITY: 기본 키 생성을 데이터베이스에 위임한다.
            id 값을 null로 하면 DB가 알아서 AUTO_INCREMENT 를 수행한다.
    - GenerationType.SEQUENCE: 데이터베이스 Sequence Object를 사용한다.
            DB Sequence는 유일한 값을 순서대로 생성하는 특별한 데이터베이스 오브젝트
            DB가 자동으로 숫자를 만들어준다.
            @SequenceGenerator가 필요하다.
    - GenerationType.TABLE: 키 생성 전용 테이블 하나를 만들어 데이터베이스 Sequence 를 흉내내는 전략
            @TableGenerator가 필요하다.
 */
