package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardService;
import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

//    @Autowired
//    private BoardRepository boardRepository;


    @GetMapping("/board")
    public List<Board> list(){
        List<Board> boards = boardService.getBoards();
        return boards;
    }

    @PostMapping("/board")
    public ResponseEntity<?> create(
            @RequestBody Board resource
    ) throws URISyntaxException {
        //게시할 글의 정보를 얻는다.
        String author = resource.getAuthor();
        String img = resource.getImg();
        String contents = resource.getContents();
        String writeTime = resource.getWriteTime();

        //글을 게시한다.
        Board board = boardService
                .addBoard(author,img,contents,writeTime);

        //url에 게시글을 띄운다.
        String url = "/board/" + board.getAuthor();
        return ResponseEntity.created(new URI(url)).body("{}");
    }


//
//    @GetMapping("/board/{author}")
//    public List<Board> read(@PathVariable("author") String author){
//        List<Board> boards = boardRepository.findAll();
//        return boards;
//    }

//
//    public void delete(){
//
//
//
//    }

}
