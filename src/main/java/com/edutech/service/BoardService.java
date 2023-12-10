package com.edutech.service;

import com.edutech.dto.BoardDTO;
import com.edutech.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    // 자유게시판 목록
    List<BoardDTO> findAll();
    // 자유게시판 상세보기
    BoardDTO findByBno(Integer bno);
    // 자유게시판 글쓰기
    Integer register(BoardDTO boardDTO);
    // 자유게시판 수정하기
    void modify(BoardDTO boardDTO);
    // 자유게시판 삭제하기
    void remove(Integer bno);
}