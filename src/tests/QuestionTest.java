package tests;

import model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionTest {

    private Question question;

    @BeforeEach
    void setUp() {
        // Initialize a sample question for testing
        question = new Question("What is the capital of France?", "Paris");
    }

    @Test
    void getQuestion() {
        assertEquals("What is the capital of France?", question.getQuestion());
    }

    @Test
    void getAnswer() {
        assertEquals("Paris", question.getAnswer());
    }

    @Test
    void getQuestionType() {
        assertEquals("DEFAULT", question.getQuestionType());
    }

    @Test
    void setMyType() throws Exception {
        // Use reflection to access the protected method
        Method setMyType = Question.class.getDeclaredMethod("setMyType", Question.Type.class);
        setMyType.setAccessible(true);
        setMyType.invoke(question, Question.Type.MULTIPLE_CHOICE);

        assertEquals("MULTIPLE_CHOICE", question.getQuestionType());
    }

    @Test
    void getMyType() {
        assertEquals("DEFAULT", question.getMyType().toString());
    }

    @Test
    void testToString() {
        String expectedToString = "Trivia Question\n{ Question = What is the capital of France?\nAnswer = Paris\nType = DEFAULT }\n";
        assertEquals(expectedToString, question.toString());
    }
}
