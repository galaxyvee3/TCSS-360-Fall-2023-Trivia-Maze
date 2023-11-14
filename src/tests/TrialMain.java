package tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import view.QuestionAnswer;

public class TrialMain {

    public static void main(String[] args) throws IOException {
        QuestionAnswer questionAnswer = new QuestionAnswer();

        List <Map <String, String>> questions = questionAnswer.getQuestions();

        System.out.println("Fetched questions:");
        for (Map<String, String> question : questions) {
            String questionText = question.get("question");
            String answerText = question.get("answer");
            System.out.println("Question: " + questionText + ", Answer: " + answerText);
        }
    }
}
