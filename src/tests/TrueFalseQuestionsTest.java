package tests;

import model.Question;
import model.ShortAnswerQuestions;
import model.TrueFalseQuestions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrueFalseQuestionsTest {

    private TrueFalseQuestions myQuestion;

    @BeforeEach
    void setUp() {
        String questionTest = "Does the limit exist?";
        String answer = "false";
        // false question.
        String question = "Stop";
        String answer2 = "no";
        myQuestion = new TrueFalseQuestions(questionTest, answer);
    }

    @Test
    void getQuestion() {
        assertEquals("Does the limit exist?", myQuestion.getQuestion());
        // question not in database.
        assertNotEquals("Stop", "no");
    }

    @Test
    void getAnswer() {
        assertEquals("false", myQuestion.getAnswer());
    }

    @Test
    void getQuestionType() {
        assertEquals(Question.Type.TRUE_FALSE, myQuestion.getMyType());
    }
}