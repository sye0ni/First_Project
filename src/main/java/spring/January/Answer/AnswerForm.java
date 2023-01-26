package spring.January.Answer;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AnswerForm {

    @NotBlank(message = "내용은 필수항목입니다.")
    private String content;
}
