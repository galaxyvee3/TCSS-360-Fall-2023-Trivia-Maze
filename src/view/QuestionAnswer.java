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
//======================Constants======================//
    /** Logger constant. **/
    private static final Logger LOGGER = Logger.getLogger(QuestionAnswer.class.getName());

    private static final String QUESTION = "QUESTION";

    private static final  String ANSWER = "ANSWER";

    private static final String QUESTION_ID = "QuestionID";

    private static final String URL = "jdbc:sqlite:QuestionsDB.db";
    /** Random constant. */
    private static final Random RANDOM = new Random();
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
        return Collections.singletonList(myQuestions.get(Integer.parseInt(QUESTION)));
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
                                    QUESTION_ID,
                                    QUESTION,
                                    ANSWER);
        } catch (final SQLException e) {
            LOGGER.severe("Question fetch from DB has failed.");
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
        final String query = "SELECT " + String.join(", ", columns) + " FROM " + tableName;
        try (Statement statement = theConn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                final Map<String, String> question = new HashMap<>();
                for (final String column : columns) {
                    question.put(column, resultSet.getString(column));
                }
                myQuestions.add(question);
            }
        }
    }

    private List<String> fetchAnswersFromDatabase() {
        List<String> allAnswers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL)) {
            allAnswers.addAll(fetchFromTable(conn, "MultipleChoiceQuestions", ANSWER));
            allAnswers.addAll(fetchFromTable(conn, "TrueFalseQuestions", ANSWER));
            allAnswers.addAll(fetchFromTable(conn, "ShortAnswerQuestions", ANSWER));
        } catch (final SQLException e) {
            LOGGER.severe("Answer fetch from DB has failed.");
        }

        return allAnswers;
    }
    public static List<String> fetchFromTable(final Connection theConn,
                                        String... theColumns) throws SQLException {
        List<String> answers = new ArrayList<>();

        String queryTF = "SELECT " + String.join(", ", theColumns) + " FROM  + TrueFalseQuestions";
        String queryMC = "SELECT " + String.join(", ", theColumns) + " FROM  + MultipleChoiceQuestions";
        String querySA = "SELECT " + String.join(", ", theColumns) + " FROM  + ShortAnswerQuestions";
        try (Statement statement = theConn.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(queryTF)) {
                statement.executeQuery(queryMC);
                statement.executeQuery(querySA);
                while (resultSet.next()) {
                    for (String column : theColumns) {
                        answers.add(resultSet.getString(column));
                    }
                }
            }
        }

        return answers;
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
                                   final String param2) throws SQLException {
        Objects.requireNonNull(theType);
        Objects.requireNonNull(param1);
        Objects.requireNonNull(param2);

        return switch (theType.toLowerCase()) {
            case "true/false" -> new TrueFalseQuestions(param1, parseBoolean(param2));
            case "multiple choice" -> new MultipleChoiceQuestions(param1, param2);
            case "short answer" -> new ShortAnswerQuestions(param1, param2);
            default -> {
                LOGGER.severe( "Invalid question type: " + theType);
                throw new IllegalArgumentException("Invalid question type: " + theType);
            }
        };
    }
    /** Helper method for true/false questions. */
    private boolean parseBoolean(final String theValue) {
        return Boolean.parseBoolean(theValue);
    }
    /**
     * Gets correct answers for short answer questions from the database.
     * @return List of correct answers for short answer questions.
     */
    public static List<String> getAnswers() {
        final List<String> correctAnswers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL)) {

            final String querySa = "SELECT ANSWER FROM ShortAnswerQuestions";
            final String queryTF = "SELECT ANSWER FROM TrueFalseQuestions";
            final String queryMc = "SELECT ANSWER FROM MultipleChoiceQuestions";

            try (Statement statementSa = conn.createStatement();
                 ResultSet resultSetSa = statementSa.executeQuery(querySa)) {
                while (resultSetSa.next()) {
                    String correctAnswer = resultSetSa.getString(ANSWER);
                    correctAnswers.add(correctAnswer);
                }
            }
            try (Statement statementTF = conn.createStatement();
                 ResultSet resultSetTF = statementTF.executeQuery(queryTF)) {
                while (resultSetTF.next()) {
                    String correctAnswer = resultSetTF.getString(ANSWER);
                    correctAnswers.add(correctAnswer);
                }
            }

            try (Statement statementMc = conn.createStatement();
                 ResultSet resultSetMc = statementMc.executeQuery(queryMc)) {
                while (resultSetMc.next()) {
                    String correctAnswer = resultSetMc.getString(ANSWER);
                    correctAnswers.add(correctAnswer);
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving answers.", e);
        }
        return correctAnswers;
    }


    /**
     * Retrieves a random question from the list of questions.
     * @return a random question.
     */
    public String getQuestionFromDatabase() {
        if (! myQuestions.isEmpty()) {
            Map<String, String> questionData = getRandomQuestion();
            return questionData.get(QUESTION);
        } else {
            return "No questions available";
        }
    }
    /**
     * Get a random question from the database.
     * @return A map containing the question data.
     */
    public Map<String, String> getRandomQuestion() {
        if (myQuestions.isEmpty()) {
            return Collections.emptyMap();
        }
        final int randomIndex = RANDOM.nextInt(myQuestions.size());
        return myQuestions.get(randomIndex);
    }

    @Override
    public String toString() {
        return "QuestionAnswer { " + "myQuestions  = " + myQuestions + " }";
    }
}
