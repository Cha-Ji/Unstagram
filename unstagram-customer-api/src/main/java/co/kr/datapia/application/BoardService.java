package co.kr.datapia.application;

import co.kr.datapia.domain.BoardRepository;
import co.kr.datapia.domain.BoardRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    //생성자
    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

}
