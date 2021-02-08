package co.kr.datapia.application;

import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    //생성자
    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public Board addBoard(String author, String img, String contents, LocalDateTime writeTime) {
        Board board = Board.builder()
                .author(author)
                .img(img)
                .contents(contents)
                .writeTime(writeTime)
                .build();
        //TODO: id는 어떻게 지정할까?
        return boardRepository.save(board);
    }

    public List<Board> getBoards() {

        return boardRepository.findAll();
    }

    public void updateBoard(String author, Long id, String img, String contents, LocalDateTime writeTime) {
        Board board = boardRepository.findByIdAndAuthor(id, author).orElse(null);

        board.setContents(contents);
        board.setWriteTime(writeTime);

    }

    public void deactivateBoard(String author, Long id) {
        boardRepository.delete(author, id);
    }
}
