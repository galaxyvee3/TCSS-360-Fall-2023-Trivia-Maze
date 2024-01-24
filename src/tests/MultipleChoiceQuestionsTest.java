package tests;

import model.MultipleChoiceQuestions;
import model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultipleChoiceQuestionsTest {
    private MultipleChoiceQuestions myQuestion;

    @BeforeEach
    void setUp() {
        // Test data
        String questionText = "What is the capital of France?";
        String answer = "Paris";
        String choiceA = "London";
        String choiceB = "Berlin";
        String choiceC = "Madrid";

        // Create an instance of MultipleChoiceQuestions
        myQuestion = new MultipleChoiceQuestions(questionText,
                                                 answer,
                                                 choiceA,
                                                 choiceB,
                                                 choiceC);
    }

    @Test
    void getQuestion() {
        assertEquals("What is the capital of France?", myQuestion.getQuestion());
    }

    @Test
    void getAnswer() {
        assertEquals("Paris", myQuestion.getAnswer());
    }

    @Test
    void getQuestionType() {
        assertEquals(Question.Type.MULTIPLE_CHOICE, myQuestion.getMyType());
    }

    @Test
    void getChoiceA() {
        assertEquals("London", myQuestion.getChoiceA());
    }

    @Test
    void getChoiceB() {
        assertEquals("Berlin", myQuestion.getChoiceB());
    }

    @Test
    void getChoiceC() {
        assertEquals("Madrid", myQuestion.getChoiceC());
    }
}