package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardService;
import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardRepository;
import co.kr.datapia.domain.BoardRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BoardController.class)
public class BoardControllerTests {

    @Autowired
    private MockMvc mvc;

    //의존성 주입 => 스프링이 관리
    @SpyBean(BoardRepositoryImpl.class)
    private BoardRepositoryImpl boardRepositoryImpl;

    @Test
    public void list() throws Exception {
        //GetMapping test
        List<Board> boards = new ArrayList<>();

        boards.add(Board.builder()
                .author("ChaJi")
                .img("winter")
                .contents("so cold")
                .writeTime("Tue Jan 26 2021 17:00:00 KST")
                .build()
        );

        //mvc.perform("/board")로 기대되는 값을 비교한다.
        mvc.perform(get("/board"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString(
                                "\"author\":\"ChaJi\"")));

    }

//    @Test
//    public void read() throws Exception {
//        mvc.perform(get("/board/ChaJi"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(
//                        containsString("so cold")
//                ));
//    }

    @Test
    public void create() throws Exception {
        mvc.perform(post("/board"))
                .andExpect(status().isOk());
    }
}