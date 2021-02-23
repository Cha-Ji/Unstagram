package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardService;
import co.kr.datapia.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BoardController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class BoardControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BoardService boardService;

    @Test
    public void list() throws Exception {
        //GetMapping test
        List<Board> boards = new ArrayList<>();

        //글 게시
        Board board = new Board();

        Long id = 1L;
        String author       = "ChaJi";
        String img          = "winter";
        String contents     = "so cold";

        boards.add(Board.builder()
                        .id(id)
                        .author(author)
                        .img(img)
                        .contents(contents)
//                        .writeTime(writeTime)
                        .build());

        given(boardService.getBoards())
                .willReturn(boards);

        Board board1 = boards.get(0);
        //mvc.perform("/board")로 기대되는 값을 비교한다.
        mvc.perform(get("/board"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ChaJi")));

    }


    @Test
    public void create() throws Exception {
        //글 게시
        Board board = new Board();

        Long id = 1L;
        String author       = "ChaJi";
        String img          = "winter";
        String contents     = "so cold";
        LocalDateTime writeTime = board.getCreatedDate();

        board = Board.builder()
                .id(id)
                .author(author)
                .img(img)
                .contents(contents)
                .build();

        given(boardService.addBoard(id, author, img, contents))
                .willReturn(board);

        mvc.perform(post("/board")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1," +
                        "\"author\":\"ChaJi\"," +
                        "\"img\":\"winter\"," +
                        "\"contents\":\"so cold\"}\n"))
                .andExpect(status().isCreated())
                .andExpect(content().string("{}"));
        verify(boardService).addBoard(id, author, img, contents);
    }

    @Test
    public void update() throws Exception {
        mvc.perform(patch("/board/ChaJi/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"author\":\"ChaJi\"," +
                        "\"contents\":\"so sweet\"}" ))
                .andExpect(status().isOk());

    }

    @Test
    public void deactivate() throws Exception {
        mvc.perform(delete("/board/ChaJi/1"))
                .andExpect(status().isOk());

        verify(boardService).deactivateBoard(1L, "ChaJi");
    }

}