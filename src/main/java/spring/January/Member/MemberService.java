package spring.January.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(MemberCreateDto memberCreateDto){ // 회원 생성 ~
        memberRepository.save(memberCreateDto.toEntity(memberCreateDto,passwordEncoder));
    }

}
