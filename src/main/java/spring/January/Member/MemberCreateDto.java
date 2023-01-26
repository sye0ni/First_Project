package spring.January.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
@NoArgsConstructor
public class MemberCreateDto {
    private String username;
    private String password;
    private String email;


    @Builder
    public MemberCreateDto(String username,String password,String email){
        this.username=username;
        this.password=password;
        this.email=email;
    }

    public Member toEntity(MemberCreateDto memberCreateDto,PasswordEncoder passwordEncoder){
        return Member.builder()
                .username(username)
                .password(passwordEncoder.encode(memberCreateDto.getPassword())) // password 암호화
                .email(email)
                .build();
    }

}
