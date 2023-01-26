package spring.January.Answer;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.January.Question.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("/answer")
@Controller
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;


    @PostMapping("/create/{id}")
    public String createAnswer(Model model,@Valid AnswerForm answerForm, BindingResult bindingResult, @PathVariable("id") Integer id) {
        // Answer 객체 생성을 위해 연결할 Question 객체 찾기
        Question question=questionService.findEntityById(id);

        // QuestionResponseDto questionResponseDto=questionService.findById(id);
        // Question question=new QuestionCreateRequestDto(questionResponseDto.getTitle(), questionResponseDto.getContent(), questionResponseDto.getCreateDate()).toEntity();

        if(bindingResult.hasErrors()){ // question_detail 템플릿에서는 question 을 필요로 하니까 추가해야함
            model.addAttribute("question",question);
            return "question_detail";
        }

        // Answer 객체 생성
        AnswerCreateRequestDto answerCreateRequestDto = new AnswerCreateRequestDto(answerForm.getContent(),LocalDateTime.now(),question);
        answerService.create(answerCreateRequestDto,question);
        return "redirect:/question/detail/{id}";
    }


}
