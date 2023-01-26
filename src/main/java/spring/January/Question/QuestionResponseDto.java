package spring.January.Question;

import lombok.Getter;
import spring.January.Answer.Answer;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class QuestionResponseDto {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private List<Answer> answerList;

    //repository 를 통해 조회한 entity -> dto 변환하는 용도
    public QuestionResponseDto(Question entity){
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.content=entity.getContent();
        this.createDate=entity.getCreateDate();
        this.answerList=entity.getAnswerList();
    }
}
