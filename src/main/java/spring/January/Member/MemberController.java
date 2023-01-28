package spring.January.Member;


import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.January.Question.QuestionCreateRequestDto;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String createMember(Model model){
        model.addAttribute("memberForm",new MemberForm()); // 이게 없으면 받을 애가 없어서 오류가 남
        return "join_form";
    }

    @PostMapping("/join")
    public String createMember( @Valid MemberForm memberForm, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            return "join_form";
        }
        if(!memberForm.getPassword1().equals(memberForm.getPassword2())){
            bindingResult.rejectValue("password2","passwordIncorrect","패스워드가 일치하지 않습니다.");
            return "join_form";
        }
        try{
            MemberCreateDto memberCreateDto=new MemberCreateDto(memberForm.getUsername(),memberForm.getPassword1(),memberForm.getEmail());
            memberService.create(memberCreateDto);
        } catch(DataIntegrityViolationException e){ // 이미 등록된 아이디나 이메일을 다시 입력
            e.printStackTrace(); // 취약한 함수라고 함 -> 리팩토링 필수
            bindingResult.reject("joinFailed","이미 등록된 사용자입니다.");
            return "join_form";
        }catch(Exception e){
            e.printStackTrace();
            bindingResult.reject("joinFailed",e.getMessage());
            return "join_form";
        }
        return "redirect:/";
    }


    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("");
        return "login_form";
    }

}
