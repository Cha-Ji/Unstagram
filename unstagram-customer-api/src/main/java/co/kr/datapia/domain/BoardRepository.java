package co.kr.datapia.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends CrudRepository<Board, Long> {
    List<Board> findAll();

    Board save(Board board);

    Optional<Board> findByAuthor(String author);

    Optional<Board> findByIdAndAuthor(Long Id, String author);
}
