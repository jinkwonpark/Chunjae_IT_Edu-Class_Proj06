package com.edutech.dto;

// DTO의 목적
// 1. Repository에 있는 테이블 데이터를 서비스에서 자바 클래스의 객체 안에 저장할 객체가 필요한데 그 클래스 객체를 DTO라고 한다.
// 2. 폼 데이터를 저장하고, 제한하여 폼의 검증을 하려는 목적

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String address;

    private Role role;

    private String createdBy;

    private String modifiedBy;
}
