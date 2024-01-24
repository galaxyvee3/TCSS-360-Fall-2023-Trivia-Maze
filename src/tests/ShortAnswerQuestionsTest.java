package tests;

import model.Question;
import model.ShortAnswerQuestions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortAnswerQuestionsTest {
    private ShortAnswerQuestions question;

    @BeforeEach
    void setUp() {
        // Test data
        String questionText = "What is the capital of Japan?";
        String answer = "Tokyo";

        // Create an instance of ShortAnswerQuestions
        question = new ShortAnswerQuestions(questionText, answer);
    }

    @Test
    void getQuestion() {
        assertEquals("What is the capital of Japan?", question.getQuestion());
    }

    @Test
    void getAnswer() {
        assertEquals("Tokyo", question.getAnswer());
    }

    @Test
    void getQuestionType() {
        assertEquals(Question.Type.SHORT_ANSWER, question.getMyType());
    }
}