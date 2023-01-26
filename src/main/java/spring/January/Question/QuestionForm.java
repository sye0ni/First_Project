package spring.January.Question;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class QuestionForm {

    @NotBlank(message = "제목은 필수항목입니다.")
    @Size(max=200)
    private String title;

    @NotBlank(message = "내용은 필수항목입니다.")
    private String content;


}
