package dk.kea.projekt1_gruppe3_maven.Test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dk.kea.projekt1_gruppe3_maven.Model.Question;
import dk.kea.projekt1_gruppe3_maven.Service.QuestionService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuestionServiceTest {
    @Autowired
    QuestionService questionService;

    @Test
    void testGetQuestionByNumber() throws Exception{
        int questionNo = 1;
        int quizType = 1;




        Question question = questionService.getQuestionByNumber(questionNo, quizType);

        // asserts

        assertEquals(questionNo, question.getQuestionNumber(), "Question number should match");
    }
}