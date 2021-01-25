package co.kr.datapia.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BoardRepositoryImpl implements BoardRepository {
    private List<Board> boards = new ArrayList<>();

    public BoardRepositoryImpl(){
        boards.add(new Board("ChaJi"));
        boards.add(new Board("MinHo"));

    }

    @Override
    public List<Board> findAll(){
        return boards;
    }

//    Board findByAuthor(String author);
}
