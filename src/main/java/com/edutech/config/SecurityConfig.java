package com.edutech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 접근제어방법2(추천) : Controller의 해당 요청자의 위에 @PreAuthorize 어노테이션을  활용해 접근 제어가 개별적으로 가능해짐, @EnableWebSecurity가 있어야 됨
public class SecurityConfig {

    /*
    접근제어방법2(추천) 예시
    아래 내용을 BoardController의 해당 요청자인 "/board/read" URL이 있는 곳 위에 기재
    @PreAuthorize("hasAnyRole('USER', 'TEACHER', 'ADMIN')")
    @GetMapping("/board/read")
    */

    // 등록 후 실행하면 로그인 창에 입력한 비밀번호가 암호화 되어 있어야 하므로 암호화된 비밀번호를 입력하여야 로그인이 이루어진다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 등록 후 실행하면 로그인 창을 찾지 못하므로 view(jsp나 Thymeleaf)를 작성한 후 formLogin() 메소드로 반드시 로그인 창을 지정하여 설정하여야 한다.
    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.build();
    }
    */


    // 접근 제어 설정 요령 : 가장 넓은 경로부터 지정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf 비활성화
        // http.csrf().disable(); 3.1 전 버전
        http.csrf((csrf) -> csrf.disable()); // 스프링부트 3.1부터 람다식 사용해야 함
        // http.authorizeHttpRequests().requestMatchers("/**").permitAll(); 3.1 전 버전
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers(new AntPathRequestMatcher("/**")).permitAll());
        // 접근제어방법1(비추천) : http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers(new AntPathRequestMatcher("/board/read")).hasRole("USER").anyRequest().authenticated());

        // 로그인 설정
        http.formLogin((formLogin) -> formLogin
                .loginPage("/member/login")
                .loginProcessingUrl("/member/loginPro")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/member/login/error"));
        // 로그아웃 설정
        http.logout((logout) -> logout
                .logoutUrl("/member/logout")
                .logoutSuccessUrl("/"));
        return http.build();
    }
}