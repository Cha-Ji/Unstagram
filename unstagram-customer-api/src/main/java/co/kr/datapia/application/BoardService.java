package co.kr.datapia.application;

import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class BoardService {

    private BoardRepository boardRepository;
    //생성자
    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public List<Board> getBoards() {

        return boardRepository.findAll();
    }

    public Board addBoard(Long id, String author, String img, String contents) {
        Board board = Board.builder()
                .id(id)
                .author(author)
                .img(img)
                .contents(contents)
                .build();
        boardRepository.save(board);

        return board;
    }


    public Board updateBoard(Long id, String author, String contents) {
        Board board = boardRepository.findByIdAndAuthor(id, author).orElse(null);

        board.setContents(contents);
        return board;
    }

    public Board deactivateBoard(Long id, String author) {
        Board board = boardRepository.findByIdAndAuthor(id, author).orElse(null);
        boardRepository.delete(board);

        return board;
    }
}
