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
 * Question_Answer class for Trivia Maze.
 * @author Viktoria Dolojan
 */
public class Question_Answer {
    private static final Logger LOGGER = Logger.getLogger(Question_Answer.class.getName());
    private List<Map<String, String>> myQuestions;
    public Question_Answer() {
        myQuestions = new ArrayList<>();
        fetchQuestionsFromDatabase();
    }

    public List<Map<String, String>> getQuestions() {
        return myQuestions;
    }


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

    @Override
    public String toString() {
        return "Question_Answer{" + "myQuestions =" + myQuestions + '}';
    }
}