package tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import model.ClueManager;
import view.QuestionAnswer;

public class TrialMain {

    public static void main(String[] args) throws IOException {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        System.out.println(questionAnswer.getQuestions());
    }
}

