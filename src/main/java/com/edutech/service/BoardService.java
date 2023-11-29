package com.edutech.service;

import com.edutech.dto.BoardDTO;
import com.edutech.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    BoardDTO findByBno(Integer bno);
    List<BoardDTO> findAll();
    Integer register(BoardDTO boardDTO);
}