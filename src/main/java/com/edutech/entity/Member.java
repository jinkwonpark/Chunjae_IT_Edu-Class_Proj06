package com.edutech.entity;

import com.edutech.dto.MemberFormDTO;
import com.edutech.dto.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@ToString
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true) // unique : 중복 안됨
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreatedBy // By 붙으면 String으로 받아야 됨
    @Column(updatable = false)
    private String createBy;

    @LastModifiedBy
    private String modifiedBy;

    public static Member createMember(MemberFormDTO memberFormDTO, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDTO.getName());
        member.setEmail(memberFormDTO.getEmail());
        member.setPassword(passwordEncoder.encode(memberFormDTO.getPassword()));
        // String password = passwordEncoder.encode(memberFormDTO.getPassword());
        // member.setPassword(password);
        member.setAddress(member.getAddress());
        // member.setCreatedBy(new Date().toString());
        member.setRole(Role.USER);
        return member;
    }
}