package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardService;
import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepositoryImpl boardRepositoryImpl;


    @GetMapping("/board")
    public List<Board> list(){
        List<Board> boards = boardRepositoryImpl.findAll();
        return boards;
    }


    @GetMapping("/board/{author}")
    public List<Board> read(@PathVariable("author") String author){
        List<Board> boards = boardRepositoryImpl.findAll();
        return boards;
    }

    @PostMapping("/board")
    public Board create(){
        Board board = new Board("ChaJi");
        board.setContents("so cold");

        return board;
    }

    public void delete(){



    }

}
