package com.edutech.service;

import com.edutech.dto.BoardDTO;
import com.edutech.entity.Board;
import com.edutech.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    // ModelMapper는 반드시 Bean 등록을 해야 함
    private final ModelMapper modelMapper;

    //하나의 데이터만 뽑아올 때는 Optional 사용
    @Override
    public BoardDTO findByBno(Integer bno) {
        Optional<Board> result = boardRepository.findById(bno);
        BoardDTO dto = modelMapper.map(result, BoardDTO.class);
        return dto;
    }

    @Override
    public List<BoardDTO> findAll() {   // board -> BoardDTO.class 끝까지 반복
        List<Board> lst = boardRepository.findAll();
        List<BoardDTO> boardList = lst.stream().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());
        return boardList;
    }

    // 게시글 등록
    @Override
    public Integer register(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        Integer bno = boardRepository.save(board).getBno();
        return bno;
    }
}