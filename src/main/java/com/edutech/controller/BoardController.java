package com.edutech.controller;

import com.edutech.dto.BoardDTO;
import com.edutech.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 자유게시판 목록
    @GetMapping("/board/list")
    @ResponseBody
    public List<BoardDTO> listAll(){
        List<BoardDTO> boardList = boardService.findAll();
        return boardList;
    }

    // 자유게시판 상세보기
    @GetMapping("/board/read")
    @ResponseBody
    public BoardDTO findByBno(Integer bno){
        BoardDTO board = boardService.findByBno(bno);
        return board;
    }

    // 자유게시판 글쓰기
    @GetMapping("/board/write")
    public String boardWriteForm(){
        return "board/write";
    }

    @PostMapping("/board/write")
    @ResponseBody
    public Integer boardWrite(@Valid BoardDTO boardDTO){
        return boardService.register(boardDTO);
    }
}