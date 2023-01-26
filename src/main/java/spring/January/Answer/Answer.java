package spring.January.Answer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import spring.January.Question.Question;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name="Answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    private LocalDateTime createDate;

    @ManyToOne//(cascade = CascadeType.ALL)
    private Question question;

    @Builder
    public Answer(String content,LocalDateTime createDate,Question question){
        this.content=content;
        this.createDate=createDate;
        this.question=question;
    }
}
