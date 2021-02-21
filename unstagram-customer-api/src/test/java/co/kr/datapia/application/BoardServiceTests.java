package co.kr.datapia.application;

import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class BoardServiceTests {

    @Mock
    private BoardRepository boardRepository;
    private BoardService boardService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        boardService = new BoardService(boardRepository);
    }

    @Test
    public void getBoards(){
        List<Board> mockBoard = new ArrayList<>();
        mockBoard.add(Board.builder().author("ChaJi").build());

        //given
        given(boardRepository.findAll()).willReturn(mockBoard);
        List<Board> boards = boardService.getBoards();

        Board board = boards.get(0);
        //assertThat
        assertThat(board.getAuthor(), is("ChaJi"));


    }

    @Test
    public void addBoard(){
        //addBoard
        Board board = new Board();
        board = boardService.addBoard(
                1L,
                "ChaJi",
                "winter",
                "is cold",
                board.getCreatedDate()
        );

        //verify. save(any())
        verify(boardRepository).save(any());
        //assertThat name is
        assertThat(board.getAuthor(),is("ChaJi") );
    }



    @Test
    public void deactivateBoard(){
        List<Board> mockBoard = new ArrayList<>();
        mockBoard.add(Board.builder().author("ChaJi").build());

        //given
        given(boardRepository.findAll()).willReturn(mockBoard);
        List<Board> boards = boardService.getBoards();

        Board board = boards.get(0);

        //assertThat
        assertThat(board.getAuthor(), is("ChaJi"));

    }

}