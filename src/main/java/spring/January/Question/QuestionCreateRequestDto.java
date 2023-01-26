package spring.January.Question;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class QuestionCreateRequestDto {

    private String title;
    private String content;
    private LocalDateTime createDate;

    @Builder
    public QuestionCreateRequestDto(String title,String content,LocalDateTime createDate){
        this.title=title;
        this.content=content;
        this.createDate=createDate;
    }

    public Question toEntity(){
        return Question.builder()
                .title(title)
                .content(content)
                .createDate(createDate)
                .build();
    }
}
