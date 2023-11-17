package tests;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.QuestionAnswer;

import static org.junit.jupiter.api.Assertions.*;

class QATestTest {
    QuestionAnswer questionAnswer;
    @BeforeEach
    void setUp() {
        questionAnswer = new QuestionAnswer();
    }
    @Test
    public void testFetchQuestionsFromDatabase() {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        List <Map <String, String>> questions = questionAnswer.getQuestions();

        // Ensure that the list is not null and contains some questions
        assertNotNull(questions);
        assertFalse(questions.isEmpty());

        // Print the fetched questions for manual verification
        for (Map<String, String> question : questions) {
            System.out.println("Question: " + question.get("question"));
            System.out.println("Answer: " + question.get("answer"));
        }
    }
}