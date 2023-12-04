package com.edutech.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//DTO : 데이터를 통채로 받고 통채로 주기 위해 사용
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @Builder가 있으면 @NoArgsConstructor, @AllArgsConstructor 안 써도 되지만 버전별 차이 때문에 써주는 게 좋음
public class BoardDTO {

    private Integer bno;

    @NotEmpty
    @Size(max = 200)
    private String title;

    @NotEmpty
    @Size(max = 2000)
    private String content;

    @NotEmpty
    @Size(max = 50)
    private String writer;

    private LocalDate regDate;

    private LocalDate modDate;

}