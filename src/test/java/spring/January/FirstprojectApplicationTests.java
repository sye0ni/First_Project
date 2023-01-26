package spring.January;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.January.Question.QuestionCreateRequestDto;
import spring.January.Question.QuestionService;

import java.time.LocalDateTime;

@SpringBootTest
class JanuaryApplicationTests {

	@Autowired
	private QuestionService questionService;

	@Test
	void testJpa() {
		for(int i=1;i<=200;i++){
			String title=String.format("테스트 데이터입니다:[%03d]",i);
			String content="내용무";
			QuestionCreateRequestDto questionCreateRequestDto=new QuestionCreateRequestDto(title,content, LocalDateTime.now());
			this.questionService.create(questionCreateRequestDto);
		}
	}

}
