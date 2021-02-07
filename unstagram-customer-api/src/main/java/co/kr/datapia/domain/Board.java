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
    private String author;

    @Id
    @GeneratedValue
    private Long id;

    private String img;

    @Setter
    private String contents;

    //TODO: 시간 자동으로 바꾸기
    @Setter
    private String writeTime; //"Tue Jan 19 2021 17:06:30 GMT+0900"

    public Board(String author){
        this.author = author;
    }
}
