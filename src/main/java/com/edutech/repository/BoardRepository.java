package com.edutech.repository;

import com.edutech.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    // jpql 사용시 예시 : @Query("select * from board b")
    // 사용하고 싶으면 build.gradle 가서 implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta' 추가

}
