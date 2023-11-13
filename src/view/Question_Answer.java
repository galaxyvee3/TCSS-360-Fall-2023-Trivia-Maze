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
 * Question factory for the trivia questions in the game.
 * @author Viktoria Dolojan
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class Question_Answer {
    private static final Logger LOGGER = Logger.getLogger(Question_Answer.class.getName());

    private List<Map<String, String>> myQuestions;

    /**
     * Default constructor.
     */
    public Question_Answer() {
        myQuestions = new ArrayList<>();
        fetchQuestionsFromDatabase();
    }

    /**
     *
     * @return
     */
    public List<Map<String, String>> getQuestions() {
        return myQuestions;
    }

    /**
     *
     * @param theChoice
     * @return
     */
    public boolean userAnswer(char theChoice) {
        return false;
    }

    private void fetchQuestionsFromDatabase() {
        try (final Connection connection = DriverManager.getConnection("jdbc:sqlite:QuestionsDB.db")) {
            fetchQuestionsFromTable(connection, "MultipleChoiceQuestions");
            fetchQuestionsFromTable(connection, "True_False_Questions");
            fetchQuestionsFromTable(connection, "ShortAnswerQuestions");
        } catch (final SQLException theErr) {
           LOGGER.log(Level.SEVERE, "Question fetch from DB has failed.", theErr);
        }
    }

    /**
     *
     * @param theConn
     * @param theTableName
     * @throws SQLException
     */
    private void fetchQuestionsFromTable(final Connection theConn,
                                         final String theTableName) throws SQLException {
        String query = "SELECT QUESTION, ANSWER FROM " + theTableName;
        try (final Statement statement = theConn.createStatement();
             final ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                final String questionText = resultSet.getString("QUESTION");
                final String answerText = resultSet.getString("ANSWER");

                final  Map<String, String> question = new HashMap <>();
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
        return "Question_Answer{" + "myQuestions =" + myQuestions + '}';
    }
}