package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.sqlite.SQLiteDataSource;

/**
 * QuestionDatabase class for Trivia Maze Fall 2023 Team 2.
 * @author rick_adams
 */
public class QuestionDatabase {
    private static final Logger logger = Logger.getLogger(QuestionDatabase.class.getName());

    public QuestionDatabase() {
    }

    public static void main(String[] args) {
        QuestionDatabase.initializeDatabase();

        List<String> questions = new ArrayList<>();
    }

    private static SQLiteDataSource createDataSource() {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:questions.db");
        return ds;
    }

    private static void createQuestionsTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS questions ( " +
                "QUESTION TEXT NOT NULL, " +
                "ANSWER TEXT NOT NULL )";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
        }
    }

    private static void insertQuestions(Connection conn) throws SQLException {
        String query1 = "INSERT INTO questions ( QUESTION, ANSWER ) VALUES ( 'Will a giant meteor hit?', 'Fingers crossed' )";
        String query2 = "INSERT INTO questions ( QUESTION, ANSWER ) VALUES ( 'Last forever?', 'November Rain' )";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query1);
            stmt.executeUpdate(query2);
        }
    }

    public static void initializeDatabase() {
        try (Connection conn = createDataSource().getConnection()) {
            createQuestionsTable(conn);
            insertQuestions(conn);
        } catch (SQLException e) {
            logger.severe("Database initialization failed: " + e.getMessage());
            System.exit(1);
        }
    }
}