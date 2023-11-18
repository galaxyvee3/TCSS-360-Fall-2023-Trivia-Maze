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
import java.util.Objects;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * QuestionAnswer class for Trivia Maze.
 * @author Viktoria Dolojan
 * @author rick_adams
 * @version Fall 2023
 */
public class QuestionAnswer {
//======================Constants======================//
    /** Logger constant. **/
    private static final Logger LOGGER = Logger.getLogger(QuestionAnswer.class.getName());

    private static final String QUESTION = "QUESTION";

    private static final  String ANSWER = "ANSWER";

    private static final String QUESTION_ID = "QuestionID";

    private static final String URL = "jdbc:sqlite:QuestionsDB.db";
//================Fields====================//
    private final List<Map<String, String>> myQuestions;

    /**
     * Public constructor.
     */
    public QuestionAnswer() {
        myQuestions = new ArrayList<>();
        fetchQuestionsFromDatabase();
    }

    /**
     * Public accessor method for the questions.
     * @return returns a list of the questions.
     */
    public List<Map<String, String>> getQuestions() {
        return myQuestions;
    }

    /**
     * Fetches questions from the SQLite database.
     */
    private void fetchQuestionsFromDatabase() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            fetchQuestionsFromTable(conn,
                                    "MultipleChoiceQuestions",
                                    QUESTION_ID,
                                    QUESTION,
                                    "choiceA",
                                    "choiceB",
                                    "choiceC",
                                    ANSWER);
            fetchQuestionsFromTable(conn, "TrueFalseQuestions",
                                    QUESTION_ID,
                                    QUESTION,
                                    ANSWER);
            fetchQuestionsFromTable(conn, "ShortAnswerQuestions",
                                    "QuestionID",
                                    QUESTION,
                                    ANSWER);
        } catch (final SQLException e) {
            LOGGER.log(Level.SEVERE, "Question fetch from DB has failed.", e);
        }
    }

    /**
     * Fetches questions from the question table.
     * Helper called by fetchQuestionsFromDatabase.
     * @param theConn sql connection object.
     * @param tableName table name in string.
     * @param columns column names in string.
     * @throws SQLException throws an SQL exception if an invalid entry is made.
     */
    private void fetchQuestionsFromTable(final Connection theConn,
                                         final String tableName,
                                         final String... columns) throws SQLException {
        final StringJoiner columnList = new StringJoiner(", ");
        for (String column : columns) {
            columnList.add(column);
        }

        final String query = "SELECT " + columnList + " FROM " + tableName;
        try (Statement statement = theConn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                final Map<String, String> question = new HashMap<>();
                for (String column : columns) {
                    question.put(column, resultSet.getString(column));
                }
                myQuestions.add(question);
            }
        }
    }
    /**
     * Question maker that can be called to create question objects.
     * Factory design.
     * @param theType the question type.
     * @param param1 the question in string.
     * @param param2 the answer in string.
     * @return returns Question - type object.
     */
    public Question createQuestion(final String theType,
                                   final String param1,
                                   final String param2) {
        try {
            Objects.requireNonNull(theType);
            Objects.requireNonNull(param1);
            Objects.requireNonNull(param2);

            return switch (theType.toLowerCase()) {
                case "true/false" -> new TrueFalseQuestions(param1, param2);
                case "multiple choice" -> new MultipleChoiceQuestions(param1, param2);
                case "short answer" -> new ShortAnswerQuestions(param1, param2);
                default -> {
                    LOGGER.log(Level.SEVERE, "Invalid question type: " + theType);
                    throw new IllegalArgumentException("Invalid question type: " + theType);
                }
            };
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "Invalid parameters for creating a question.", e);
            throw e;
        }
    }

    @Override
    public String toString() {
        return "QuestionAnswer { " + "myQuestions  = " + myQuestions + " }";
    }
}
