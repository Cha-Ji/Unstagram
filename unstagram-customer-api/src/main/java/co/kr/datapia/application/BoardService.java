package co.kr.datapia.application;

import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    //생성자
    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public Board addBoard(String author, String img, String contents, String writeTime) {
        Board board = Board.builder()
                .author(author)
                .img(img)
                .contents(contents)
                .writeTime(writeTime)
                .build();

        return boardRepository.save(board);
    }

    public List<Board> getBoards() {
        List<Board> boards = boardRepository.findAll();

        return boards;
    }

    public void updateBoard(String author, Long id, String img, String contents, String writeTime) {
    // TODO: findById로 바꾸자
        Board board = boardRepository.findByAuthor(author).orElse(null);

        board.setContents(contents);
        board.setWriteTime(writeTime);

    }

    public Board deactivateBoard(String author, Long id) {
        Board board = boardRepository.findByAuthor(author).orElse(null);

        return board;
    }
}
