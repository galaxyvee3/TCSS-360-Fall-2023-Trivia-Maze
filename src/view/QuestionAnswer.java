package view;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * QuestionAnswer class for Trivia Maze.
 * @author Viktoria Dolojan
 * @author rick_adams
 * @version Fall 2023
 */
public class QuestionAnswer {
    private static final Logger LOGGER = Logger.getLogger(QuestionAnswer.class.getName());
    private final List<Map<String, String>> myQuestions;

    /**
     * Default constructor.
     */
    public QuestionAnswer() {
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
     */
    private void fetchQuestionsFromDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:QuestionsDB.db")) {
            fetchQuestionsFromTable(conn, "MultipleChoiceQuestions");
            fetchQuestionsFromTable(conn, "TrueFalseQuestions");
            fetchQuestionsFromTable(conn, "ShortAnswerQuestions");
        } catch (final SQLException e) {
            LOGGER.log(Level.SEVERE, "Question fetch from DB has failed.", e);
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
        final String query = "SELECT QUESTION, ANSWER FROM " + theTableName;
        try (Statement statement = theConn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                final String questionText = resultSet.getString("QUESTION");
                final String answerText = resultSet.getString("ANSWER");

                final  Map<String, String> question = new HashMap<>();
                question.put("question", questionText);
                question.put("answer", answerText);

                myQuestions.add(question);
            }
        }
    }
    private void fetchMultipleChoiceQuestions(final Connection theConn) throws SQLException {
        final String query = "SELECT QuestionID, QUESTION, choiceA, choiceB, choiceC, ANSWER FROM MultipleChoiceQuestions";
        try (Statement statement = theConn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                final String questionID = resultSet.getString("QuestionID");
                final String questionText = resultSet.getString("QUESTION");
                final String choiceA = resultSet.getString("choiceA");
                final String choiceB = resultSet.getString("choiceB");
                final String choiceC = resultSet.getString("choiceC");
                final String answerText = resultSet.getString("ANSWER");

                // Create a map to represent the question
                final Map<String, String> question = new HashMap<>();
                question.put("questionID", questionID);
                question.put("questionText", questionText);
                question.put("choiceA", choiceA);
                question.put("choiceB", choiceB);
                question.put("choiceC", choiceC);
                question.put("answer", answerText);

                myQuestions.add(question);
            }
        }
    }

// Similar methods for TrueFalseQuestions and ShortAnswerQuestions

    public Question createQuestion(final String type,
                                   final String param1,
                                   final String param2) {
        try {
            Objects.requireNonNull(type);
            Objects.requireNonNull(param1);
            Objects.requireNonNull(param2);

            return switch (type.toLowerCase()) {
                case "true/false" -> new TrueFalseQuestions(param1, param2);
                case "multiple choice" -> new MultipleChoiceQuestions(param1, param2);
                case "short answer" -> new ShortAnswerQuestions(param1, param2);
                default -> {
                    LOGGER.log(Level.SEVERE, "Invalid question type: " + type);
                    throw new IllegalArgumentException("Invalid question type: " + type);
                }
            };
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "Invalid parameters for creating a question.", e);
            throw e;
        }
    }


    @Override
        public String toString() {
        String sb = "QuestionAnswer { " + "myQuestions  = " + myQuestions +
                '}';
            return sb;
        }
    }
