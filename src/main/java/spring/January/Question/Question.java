package spring.January.Question;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import spring.January.Answer.Answer;
import spring.January.Answer.AnswerCreateRequestDto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
@Table(name="Question")
public class Question {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Integer id;

    @Column(length=100)
    @NotEmpty(message="제목은 필수항목입니다.")
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotEmpty(message="내용은 필수항목입니다.")
    private String content;

    @CreatedDate
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE)
    private List<Answer> answerList=new ArrayList<>();

    @Builder
    public Question(String title,String content,LocalDateTime createDate){
        this.title=title;
        this.content=content;
        this.createDate=createDate;
    }

    public void addAnswer(AnswerCreateRequestDto answerCreateRequestDto){
        this.answerList.add(answerCreateRequestDto.toEntity());
    } // 답변 생성되면, 질문의 answerList 에 답변을 추가해준다.

}
