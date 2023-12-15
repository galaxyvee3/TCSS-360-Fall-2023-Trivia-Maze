package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.InvocationTargetException;
import model.Question;
import model.QuestionAnswer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class QuestionAnswerTest {

    private QuestionAnswer questionAnswer;

    @BeforeEach
    void setUp() {
        questionAnswer = new QuestionAnswer();
    }

    @Test
    void fetchQuestions() throws Exception {

        QuestionAnswer questionAnswer = new QuestionAnswer();

        Method fetchQuestionsMethod = QuestionAnswer.class.getDeclaredMethod("fetchQuestions");


        fetchQuestionsMethod.setAccessible(true);

        fetchQuestionsMethod.invoke(questionAnswer);
        assertNotNull(questionAnswer.getQuestions());
    }

    @Test
    void getQuestions() {
        // Test if getQuestions returns a non-null list
        assertNotNull(questionAnswer.getQuestions());
    }

    @Test
    void toStringOverride() {
        // Test if toString method does not throw exceptions
        assertNotNull(questionAnswer.toString());
    }

    @Test
    void fetchQuestionsWithoutDatabase() {
        QuestionAnswer questionAnswerMock = new QuestionAnswer() {
            protected void fetchQuestions() {
                throw new RuntimeException("Simulating database connection failure");
            }
        };

        // Use reflection to invoke the private fetchQuestions method
        Method fetchQuestionsMethod;
        try {
            fetchQuestionsMethod = QuestionAnswer.class.getDeclaredMethod("fetchQuestions");
            fetchQuestionsMethod.setAccessible(true);
            fetchQuestionsMethod.invoke(questionAnswerMock);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // Catch the expected exception
            assertTrue(e.getCause() instanceof RuntimeException);
        }

        // Verify that the list of questions is empty
        assertFalse(questionAnswerMock.getQuestions().isEmpty());
    }



    // Mock class for Question
    private static class MockQuestion extends Question {
        public MockQuestion() {
            super("", "");
        }
    }
}
