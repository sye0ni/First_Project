package spring.January.Question;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.January.Answer.AnswerForm;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor // final 변수에 DI
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public String init(Model model, @PageableDefault(size=10,sort="createDate",direction= Sort.Direction.DESC) Pageable pageable){ // 질문 등록 화면 띄움 , 아직까지는 루트 화면
        Page<QuestionResponseDto> list=questionService.getList(pageable);
        model.addAttribute("paging",list);
        return "question_list"; // controller 에서 문자열을 반환하면 viewResolver가 화면을 찾아서 처리함
    }

    @GetMapping("/create")
    public String createQuestion(QuestionForm questionForm){
        return "question_form";
    }

    /*@PostMapping("/create") // 질문 등록
    public String createQuestion(Model model, @RequestParam String title,@RequestParam String content){
        QuestionCreateRequestDto questionCreateRequestDto=new QuestionCreateRequestDto(title,content, LocalDateTime.now());
        questionService.create(questionCreateRequestDto);
        return "redirect:/question/list";
    }*/

    @PostMapping("/create") // 질문 등록
    public String createQuestion(@Valid QuestionForm questionForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "question_form";
        }
        QuestionCreateRequestDto questionCreateRequestDto=new QuestionCreateRequestDto(questionForm.getTitle(),questionForm.getContent(),LocalDateTime.now());
        questionService.create(questionCreateRequestDto);
        return "redirect:/question/list";
    }

    @GetMapping("/detail/{id}") // 질문 상세 화면
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        QuestionResponseDto questionResponseDto=questionService.findById(id);
        model.addAttribute("question",questionResponseDto);
        return "question_detail";
    }
}
