package co.kr.datapia.domain;

import java.util.List;

public interface BoardRepository {
    List<Board> findAll();
}
