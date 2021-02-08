package co.kr.datapia.domain;

import co.kr.datapia.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

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

    @Setter
    @NotEmpty
    private LocalDateTime writeTime;

    public Board(String author){
        this.author = author;
    }
}
