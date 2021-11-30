package com.springbootguestbook.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// @MappedSuperclass 를 적용하면 이 추상클래스의 객체들은 DB에 테이블로 생성되지 않지만,
// 이 클래스를 상속하는 Entity 클래스로 DB 테이블이 생성된다.
@MappedSuperclass

// AuditingEntityListener 는 Jpa 내부에서 Entity 객체가 생성되거나 변경되는 것을 감지한다.
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
abstract class BaseEntity {

    // 객체의 최초 생성시간 관리.
    // updatable = false 로 설정하였기 때문에, DB에 반영할 때
    // 이 객체의 컬럼 값은 반영되지 않는다.
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    // 객체의 최종 수정시간 관리.
    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;

}
