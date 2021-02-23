package co.kr.datapia.domain;

import co.kr.datapia.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board extends BaseTimeEntity {
    private String author;

    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @NotEmpty
    private String img;

    @Setter
    @NotEmpty
    private String contents;

    public Board(Long id, String author){
        this.id = id;
        this.author = author;
    }
}
