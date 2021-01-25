package co.kr.datapia.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {
    List<Board> findAll();

    Board save(Board board);
}
