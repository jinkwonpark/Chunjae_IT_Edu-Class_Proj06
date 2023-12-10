package com.edutech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 생성시, 수정시 타임스탬프가 DB에 저장
public class EdutechApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdutechApplication.class, args);
    }

}
