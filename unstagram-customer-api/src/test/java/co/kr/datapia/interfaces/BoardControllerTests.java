package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardService;
import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BoardController.class)
public class BoardControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BoardService boardService;

    @Test
    public void list() throws Exception {
        //GetMapping test
        List<Board> boards = new ArrayList<>();

        boards.add(Board.builder()
                .author("ChaJi")
                .build()
        );

        given(boardService.getBoards()).willReturn(boards);


        //mvc.perform("/board")로 기대되는 값을 비교한다.
        mvc.perform(get("/board"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("ChaJi")));

    }


    @Test
    public void create() throws Exception {
        //글 게시
        String author       = "ChaJi";
        String img          = "winter";
        String contents     = "so cold";
        String writeTime    = "Tue Jan 26 2021 17:00:00 KST";

        Board board = Board.builder()
                .author(author)
                .img(img)
                .contents(contents)
                .writeTime(writeTime)
                .build();

        given(boardService.addBoard(author, img, contents, writeTime)).willReturn(board);

        mvc.perform(post("/board")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"author\":\"ChaJi\"," +
                        "\"img\":\"winter\"," +
                        "\"contents\":\"so cold\"," +
                        "\"writeTime\":\"Tue Jan 26 2021 17:00:00 KST\""))
                .andExpect(status().isCreated());

        verify(boardService).addBoard(author, img, contents, writeTime);
    }

//    @Test
//    public void read() throws Exception {
//
//        mvc.perform(get("/board/ChaJi"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(
//                        containsString("so cold")
//                ));
//    }

}