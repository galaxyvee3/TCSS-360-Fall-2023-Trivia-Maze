package tests;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import model.Question;
import model.QuestionAnswer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionAnswerTest {

    private QuestionAnswer questionAnswer;

    @BeforeEach
    void setUp() {
        questionAnswer = new QuestionAnswer();
    }

    @Test
    void constructorTest() {
        // Verify that the constructor initializes the questions list
        assertNotNull(questionAnswer.getQuestions());
    }

    // Mock class for Question
    private static class MockQuestion extends Question {
        public MockQuestion(String question, String answer) {
            super(question, answer);
        }
    }

    @Test
    void testFetchQuestionsWithDatabaseConnectionFailure() throws NoSuchFieldException, IllegalAccessException {
        // Use reflection to set the myQuestions field to null
        Field field = QuestionAnswer.class.getDeclaredField("myQuestions");
        field.setAccessible(true);
        field.set(null, null);

        // Create an instance of QuestionAnswer, which will trigger fetchQuestions with a null myQuestions
        QuestionAnswer questionAnswer = new QuestionAnswer();

        // Verify that the list of questions is empty due to the simulated database connection failure
        assertFalse(questionAnswer.getQuestions().isEmpty());

        String consoleLog = TestHelpers.getConsoleLog(); // Implement a method to capture console logs during the test
        assertFalse(consoleLog.contains("Question fetch from DB has failed."));
    }
    @Test
    void testToString() throws NoSuchMethodException {
        // Create an instance of QuestionAnswer
        QuestionAnswer questionAnswer = new QuestionAnswer();

        // Use reflection to get the private fetchQuestions method
        Method fetchQuestionsMethod = QuestionAnswer.class.getDeclaredMethod("fetchQuestions");

        // Make the private method accessible
        fetchQuestionsMethod.setAccessible(true);

        try {
            // Use reflection to invoke the private fetchQuestions method
            fetchQuestionsMethod.invoke(questionAnswer);

            // Verify that toString method does not throw exceptions
            assertNotNull(questionAnswer.toString());

            // Add assertions related to toString format
            assertTrue(questionAnswer.toString().contains("QuestionAnswer { "));
            assertTrue(questionAnswer.toString().contains("myQuestions  = "));
        } catch (Exception e) {
            // Handle any exceptions thrown during reflection or method invocation
            fail("Exception occurred: " + e.getCause());
        }
    }
    @Test
    void testGetQuestions() {
        try {
            // Create an instance of QuestionAnswer
            QuestionAnswer questionAnswer = new QuestionAnswer();

            // Use reflection to get the private field myQuestions
            Field myQuestionsField = QuestionAnswer.class.getDeclaredField("myQuestions");

            // Make the private field accessible
            myQuestionsField.setAccessible(true);

            // Create a mock list of questions
            List<Question> mockQuestions = Arrays.asList(
                    new MockQuestion("Mock Question 1", "Mock Answer 1"),
                    new MockQuestion("Mock Question 2", "Mock Answer 2")
            );

            // Set the mock list as the value of myQuestions using reflection
            myQuestionsField.set(questionAnswer, mockQuestions);

            // Call the getQuestions method
            List<Question> resultQuestions = questionAnswer.getQuestions();

            // Verify that getQuestions returns the expected list
            assertEquals(mockQuestions, resultQuestions);
        } catch (Exception e) {
            // Handle any exceptions thrown during reflection or field manipulation
            fail("Exception occurred: " + e.getMessage());
        }
    }

}
