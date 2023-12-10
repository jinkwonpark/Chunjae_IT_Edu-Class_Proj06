package com.edutech.dto;

import jakarta.validation.constraints.Email; // constraints : 제약 조건
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

// 폼 검증 목적 : 회원 가입 폼의 항목 수대로 기술해야 함
@Getter
@Setter
public class MemberFormDTO {

    @NotBlank(message = "이름은 필수 입력 항목입니다.") // 여기서() 어노테이션은 validation 걸리는 것으로 사용해야 한다.
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    @NotEmpty(message = "비밀번호 필수 입력 항목입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8~16 내에 입력해주시기 바랍니다.")
    private String password;

    @NotEmpty(message = "주소는 필수 입력 항목입니다.")
    private String address;

}
