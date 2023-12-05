package view;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
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
    private static final List<Map<String, String>> myQuestions  = new ArrayList<>();

    private static int myCurrentIndex;

    /**
     * Public constructor.
     */
    public QuestionAnswer() {
        myCurrentIndex = 0;
        fetchQuestionsFromDatabase();
    }

    /**
     * Public accessor method for the questions.
     * @return returns a list of the questions.
     */
//    public List<Map<String, String>> getQuestions() {
//        return Collections.singletonList(myQuestions.get(Integer.parseInt(QUESTION)));
//    }

    /**
     * Fetches questions from the SQLite database.
     */
    public static void fetchQuestionsFromDatabase() {
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
     */
    private static void fetchQuestionsFromTable(final Connection theConn, final String tableName,
                                                final String... columns) throws SQLException {
        final String query = "SELECT " + String.join(", ", columns) + " FROM " + tableName;
        try (Statement statement = theConn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                // Create a new Map for each question
                final Map<String, String> question = new ConcurrentHashMap<>();

                for (final String column : columns) {
                    question.put(column, resultSet.getString(column));
                }

                myQuestions.add(question);
            }
        }
    }


    public static List<String> fetchAnswersFromDatabase() {
        final List<String> allAnswers = new ArrayList<>();
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
                                              final String tableName,
                                              final String... theColumns) throws SQLException {
        final List<String> answers = new ArrayList<>();

        String query = "SELECT " + String.join(", ", theColumns) + " FROM " + tableName;
        try (Statement statement = theConn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                for (String column : theColumns) {
                    answers.add(resultSet.getString(column));
                }
            }
        }
        System.out.println(answers);
        return answers;
    }


    /**
     * Question maker that can be called to create question objects.
     * Factory design.
     * @param theType the question type.
     * @param theQuestion the question in string.
     * @param theAnswer the answer in string.
     * @return returns Question - type object.
     */
    public Question createQuestion(final String theType,
                                   final String theQuestion,
                                   final String theAnswer) throws SQLException {
        Objects.requireNonNull(theType);
        Objects.requireNonNull(theQuestion);
        Objects.requireNonNull(theAnswer);

        return switch (theType.toLowerCase(Locale.getDefault())) {
            case "true/false" -> new TrueFalseQuestions(theQuestion, parseBoolean(theAnswer));
            case "multiple choice" -> new MultipleChoiceQuestions(theQuestion, theAnswer);
            case "short answer" -> new ShortAnswerQuestions(theQuestion, theAnswer);
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
     * Gets correct answers from the database.
     * @return List of correct answers for the questions.
     */
    public static List<String> getAnswers() {
        List<String> correctAnswers = new ArrayList<>();
        final String[] tableNames = {"ShortAnswerQuestions",
                                     "TrueFalseQuestions",
                                     "MultipleChoiceQuestions"};

        try (Connection conn = DriverManager.getConnection(URL)) {
            for (String tableName : tableNames) {
                String query = "SELECT ANSWER FROM " + tableName;
                try (Statement statement = conn.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        String correctAnswer = resultSet.getString(ANSWER);
                        correctAnswers.add(correctAnswer);
                    }
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
    public static String getQuestionFromDatabase() {
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
    public static Map<String, String> getRandomQuestion() {
        if (myQuestions.isEmpty()) {
            return Collections.emptyMap();
        }
        final int randomIndex = RANDOM.nextInt(myQuestions.size());
        return myQuestions.get(randomIndex);
    }
    /**
     * Public accessor method for the questions.
     *
     * @return returns a list of the questions.
     */
    public static List<Map<String, String>> getQuestions() {
        if (hasMoreQuestions()) {
            return Collections.singletonList(myQuestions.get(myCurrentIndex));
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Checks if there are more questions available.
     *
     * @return true if there are more questions, false otherwise
     */
    public static boolean hasMoreQuestions() {
        return myCurrentIndex < myQuestions.size();
    }

    /**
     * Gets the next question.
     * @return the next question, or null if there are no more questions
     */
    public Map<String, String> getNextQuestion() {
        if (hasMoreQuestions()) {
            myCurrentIndex++;
            return myQuestions.get(myCurrentIndex);
        } else {
            return null;
        }
    }
    /**
     * Retrieve and display information about the question tables.
     */
    public static void displayQuestionTableInfo() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            displayTableInfo(conn, "MultipleChoiceQuestions");
            displayTableInfo(conn, "TrueFalseQuestions");
            displayTableInfo(conn, "ShortAnswerQuestions");
        } catch (final SQLException e) {
            LOGGER.severe("Error fetching question table information.");
        }
    }
    /**
     * Helper method to display information about database tables.
     * @param theConn SQL connection object.
     * @param theTableName Name of the question table.
     */
    private static void displayTableInfo(final Connection theConn, final String theTableName) throws SQLException {
        final DatabaseMetaData metaData = theConn.getMetaData();

        try (ResultSet columns = metaData.getColumns(null, null, theTableName, null)) {
            System.out.println("Columns for table " + theTableName + ":");
            while (columns.next()) {
                System.out.println("Column: " + columns.getString("COLUMN_NAME") +
                                   ", Type: " + columns.getString("TYPE_NAME"));
            }
        }

        try (ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, theTableName)) {
            System.out.println("Primary keys for table " + theTableName + ":");
            while (primaryKeys.next()) {
                System.out.println("Primary Key: " + primaryKeys.getString("COLUMN_NAME"));
            }
        }
    }

    /**
     * Generated toString.
     * @return Returns the raw QuestionAnswer class via string.
     */
    @Override
    public String toString() {
        return "QuestionAnswer { " + "myQuestions  = " + myQuestions + " }";
    }

    public static void main(String[] args) {
       displayQuestionTableInfo();
    }
}
