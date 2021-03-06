package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardService;
import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    public List<Board> list(){
        List<Board> boards = boardService.getBoards();
        return boards;
    }

    //TODO: 특정 작성자의 글
    @GetMapping("/board/{author}")
    public List<Board> boardsOfAuthor(){
        List<Board> boards = boardService.getBoards();
        return boards;
    }

    //TODO: 특정 작성자의 특정 글
    @GetMapping("/board/{author}/{id}")
    public Board boardOfAuthorAndId(){
        Board board = boardService.getBoards().get(0);
        return board;
    }


    @PostMapping("/board")
    public ResponseEntity<?> create(
            @Valid @RequestBody Board resource
    ) throws URISyntaxException {

        //게시할 글의 정보를 얻는다.
        Long    id          = resource.getId();
        String author       = resource.getAuthor();
        String img          = resource.getImg();
        String contents     = resource.getContents();
        LocalDateTime writeTime    = resource.getCreatedDate();

        //글을 게시한다.
        Board board = boardService.addBoard(
                        id, author,img,contents);

        //url에 게시글을 띄운다.
        String url = "/board/" + board.getAuthor() + board.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @PatchMapping("/board/{author}/{id}")
    public String update(
            @PathVariable("author") String author,
            @PathVariable("id") Long id,
            @RequestBody Board resource
    ){
        String contents     = resource.getContents();

        boardService.updateBoard(id, author,  contents);
        return "{}";
    }

    @DeleteMapping("/board/{author}/{id}")
    public String deactivate(
        @PathVariable("author") String author,
        @PathVariable("id") Long id
    ){
        boardService.deactivateBoard(id, author);
        return "{}";
    }


}
