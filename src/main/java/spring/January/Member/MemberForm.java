package spring.January.Member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class MemberForm {
    @Size(min=3,max=25)
    @NotEmpty(message="사용자ID는 필수항목입니다.")
    private String username;

    @NotEmpty(message="비밀번호는 필수항목입니다.")
    private String password1;

    @NotEmpty(message="비밀번호 확인은 필수항목입니다.")
    private String password2;

    @NotEmpty(message="이메일은 필수항목입니다.")
    @Email // 해당 속성의 값이 이메일 형식과 일치하는지 검증함
    private String email;
}
