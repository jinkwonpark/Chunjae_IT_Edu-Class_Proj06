package com.edutech.service;

import com.edutech.dto.BoardDTO;
import com.edutech.entity.Board;
import com.edutech.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    // ModelMapper는 반드시 Bean 등록을 해야 함
    private final ModelMapper modelMapper;

    // 자유게시판 목록
    @Override
    public List<BoardDTO> findAll() {   // board -> BoardDTO.class 끝까지 반복
        List<Board> lst = boardRepository.findAll();
        List<BoardDTO> boardList = lst.stream().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());
        return boardList;
    }

    // 자유게시판 상세보기
    //하나의 데이터만 뽑아올 때는 Optional 사용
    @Override
    public BoardDTO findByBno(Integer bno) {
        Optional<Board> result = boardRepository.findById(bno);
        BoardDTO dto = modelMapper.map(result, BoardDTO.class);
        return dto;
    }

    // 자유게시판 글쓰기
    @Override
    public Integer register(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        Integer bno = boardRepository.save(board).getBno();
        return bno;
    }

    // 자유게시판 수정하기
    @Override
    public void modify(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
        Board board = result.orElseThrow();
        board.change(boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board);
    }

    // 자유게시판 삭제하기
    @Override
    public void remove(Integer bno) {
        boardRepository.deleteById(bno);
    }
}