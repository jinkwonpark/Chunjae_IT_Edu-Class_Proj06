package com.edutech.controller;

import com.edutech.dto.BoardDTO;
import com.edutech.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    //  ---------- API ----------

    // 자유게시판 목록
    @GetMapping("/api/list")
    @ResponseBody
    public List<BoardDTO> listAll(){
        List<BoardDTO> boardList = boardService.findAll();
        return boardList;
    }

    // 자유게시판 상세보기
    @GetMapping("/api/read")
    @ResponseBody
    public BoardDTO findByBno(Integer bno){
        BoardDTO board = boardService.findByBno(bno);
        return board;
    }

    // 자유게시판 글쓰기
    @PostMapping("/api/register")
    @ResponseBody
    public Integer apiboardWrite(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("board POST register.......");
        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
        }
        Integer bno  = boardService.register(boardDTO);
        return bno;
    }

    // 자유게시판 수정하기
    @PostMapping("/api/modify")
    @ResponseBody
    public BoardDTO modifyPro(@Valid BoardDTO boardDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        log.info("board modify post......." + boardDTO);
        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            String link = "bno="+boardDTO.getBno();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return boardDTO;
        }
        boardService.modify(boardDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return boardDTO;
    }

    // 자유게시판 삭제하기
    @PostMapping("/api/remove")
    public String remove(Integer bno, RedirectAttributes redirectAttributes) {
        log.info("remove post.. " + bno);
        boardService.remove(bno);
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/apiboard/list";
    }

    //  ---------- API ----------

    // 자유게시판 목록
    @GetMapping({"board","/board/list"})
    public String boardListAll(Model model){
        List<BoardDTO> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    // 자유게시판 상세보기
    @PreAuthorize("hasAnyRole('USER', 'TEACHER', 'ADMIN')") // hasRole 사용 시 role 한 개만 설정 가능, hasAnyRole 사용 시 role 여러 개 설정 가능
    @GetMapping("/board/read")
    public String boardFindByBno(Integer bno, Model model){
        BoardDTO board = boardService.findByBno(bno);
        model.addAttribute("dto", board);
        return "board/read";
    }

    // 자유게시판 글쓰기
    @GetMapping("/board/write")
    public String boardForm(){
        return "board/write";
    }

    @PostMapping("/board/register")
    public String boardWrite(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("board POST register.......");
        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
        }
        Integer bno  = boardService.register(boardDTO);
        return "redirect:/board/list";
    }

    // 자유게시판 수정하기
    @GetMapping("/board/modify")
    public String boardModify(Integer bno, Model model){
        BoardDTO boardDTO = boardService.findByBno(bno);
        model.addAttribute("dto", boardDTO);
        return "board/modify";
    }

    @PostMapping("/board/modify")
    public BoardDTO boardModifyPro(@Valid BoardDTO boardDTO,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes){
        log.info("board modify post......." + boardDTO);
        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            String link = "bno="+boardDTO.getBno();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return boardDTO;
        }
        boardService.modify(boardDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return boardDTO;
    }

    // 자유게시판 삭제하기
    @PostMapping("/board/remove")
    public String boardRemove(Integer bno, RedirectAttributes redirectAttributes) {
        log.info("remove post.. " + bno);
        boardService.remove(bno);
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/board/list";
    }
}