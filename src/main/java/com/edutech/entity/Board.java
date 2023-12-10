package com.edutech.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Entity는 @Data 쓰지 말 것
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = { AuditingEntityListener.class })

// Entity : 물리적인 테이블과 연결하기 위해 사용
public class Board {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer bno;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    @CreationTimestamp  // @CreatedDate = LocalDate
    @Column(name = "regdate", updatable = false) // name = "regdate" : db에 들어갈 이름, updatable = false : 변경불가
    private LocalDateTime regDate;

    @UpdateTimestamp    // @LastModifiedDate = LocalDate
    @Column(name = "moddate")
    private LocalDateTime modDate;

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }
}