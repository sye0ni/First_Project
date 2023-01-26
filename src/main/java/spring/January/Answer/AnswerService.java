package spring.January.Answer;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.January.Question.Question;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(AnswerCreateRequestDto answerCreateRequestDto,Question question){
        answerRepository.save(answerCreateRequestDto.toEntity());
        question.addAnswer(answerCreateRequestDto);
    }
}
