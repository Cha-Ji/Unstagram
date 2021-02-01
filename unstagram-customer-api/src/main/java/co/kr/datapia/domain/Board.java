package co.kr.datapia.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {
    @Id
    @GeneratedValue
    private String author;

    private Long id;

    private String img; //TODO: List<String>이 안되는 이유?

    @Setter
    private String contents;

    @Setter
    private String writeTime; //"Tue Jan 19 2021 17:06:30 GMT+0900"

    public Board(String author){
        this.author = author;
    }
}
