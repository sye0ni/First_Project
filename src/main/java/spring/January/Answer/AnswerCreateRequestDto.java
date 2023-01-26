package spring.January.Answer;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.January.Question.Question;
import spring.January.Question.QuestionService;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AnswerCreateRequestDto {
    private String content;
    private LocalDateTime createDate;
    private Question question;


    @Builder
    public AnswerCreateRequestDto(String content,LocalDateTime createDate,Question question){
        this.content=content;
        this.createDate=createDate;
        this.question=question;
    }

    public Answer toEntity(){
        return Answer.builder()
                .content(content)
                .createDate(createDate)
                .question(question)
                .build();
    }


}
