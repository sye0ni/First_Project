package spring.January.Question;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    /*public List<QuestionResponseDto> getList(){ //전체 질문 리스트 반환
        List<Question> list=questionRepository.findAll(Sort.by(Sort.Direction.DESC,"id","createDate"));
        return list.stream().map(QuestionResponseDto::new).collect(Collectors.toList());
    }*/

    public Page<QuestionResponseDto> getList(Pageable pageable){ //전체 질문 리스트 반환
        Page<Question> list=questionRepository.findAll(pageable);
        Page<QuestionResponseDto> pagingList=list.map(
                question->new QuestionResponseDto(question));
        return pagingList;
    }


    public QuestionResponseDto findById(Integer id){
        Question question=questionRepository.findById(id).orElseThrow(()
            ->new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        return new QuestionResponseDto(question);
    }


    // Answer 생성시 연결할 Question을 찾는 용도, 이렇게 엔티티 반환하면 위험하니까 리팩토링 必
    public Question findEntityById(Integer id){
        Question question=questionRepository.findById(id).orElseThrow(()
            -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        return question;
    }

    public void create(QuestionCreateRequestDto questionCreateRequestDto){
        questionRepository.save(questionCreateRequestDto.toEntity());
    }
}
