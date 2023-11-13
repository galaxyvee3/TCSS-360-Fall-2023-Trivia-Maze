package view;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * QuestionAnswer class for Trivia Maze.
 * @author Viktoria Dolojan
 * @version Fall 2023
 */
public class QuestionAnswer {
    private static final Logger LOGGER = Logger.getLogger(QuestionAnswer.class.getName());
    private final List<Map<String, String>> myQuestions;
    public QuestionAnswer() {
        myQuestions = new ArrayList<>();
        fetchQuestionsFromDatabase();
    }

    public List<Map<String, String>> getQuestions() {
        return myQuestions;
    }

    public boolean userAnswer(final char theChoice) {
        return false;
    }
    private void fetchQuestionsFromDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:QuestionsDB.db")) {
            fetchQuestionsFromTable(conn, "MultipleChoiceQuestions");
            fetchQuestionsFromTable(conn, "True_False_Questions");
            fetchQuestionsFromTable(conn, "ShortAnswerQuestions");
        } catch (final SQLException e) {
            LOGGER.log(Level.SEVERE, "Question fetch from DB has failed.", e);
        }
    }
    private void fetchQuestionsFromTable(final Connection theConn,
                                         final String theTableName) throws SQLException {
        final String query = "SELECT QUESTION, ANSWER FROM " + theTableName;
        try (Statement statement = theConn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                final String questionText = resultSet.getString("QUESTION");
                final String answerText = resultSet.getString("ANSWER");

                final  Map<String, String> question = new HashMap<>();
                question.put("question", questionText);
                question.put("answer", answerText);
//                 question.setQuestionType(tableName);

                myQuestions.add(question);
            }
        }
    }
    /**
     * Question object creator.
     * (Factory design pattern).
     * @param type The question type.
     * @param param1 The question text.
     * @param param2 The Answer text.
     * @return returns Question object.
     */
    public Question createQuestion(final String type,
                                   final String param1,
                                   final String param2) {
        final Question question = switch (type.toLowerCase()) {
            case "true/false" -> new TrueFalseQuestions(param1, param2);
            case "multiple choice" -> new MultipleChoiceQuestions(param1, param2);
            case "short answer" -> new ShortAnswerQuestions(param1, param2);
            default ->
                // Handle unknown type or throw an exception
                    throw new IllegalArgumentException("Invalid question type: " + type);
        };
        return question;
    }
    @Override
    public String toString() {
        return "QuestionAnswer{" + "myQuestions =" + myQuestions + '}';
    }
}