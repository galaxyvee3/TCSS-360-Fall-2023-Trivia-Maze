package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import org.sqlite.SQLiteDataSource;

public class QuestionDatabase {
    private static final Logger logger = Logger.getLogger(QuestionDatabase.class.getName());

    private static SQLiteDataSource ds;

    public QuestionDatabase() {

    }
    private static SQLiteDataSource  createDataSource() {
        ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:questions.db");
        return ds;
    }
    private static void createQuestionsTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS questions ( " +
                       "QUESTION TEXT NOT NULL, " +
                       "ANSWER TEXT NOT NULL )";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
             stmt.executeUpdate(query);
        }
    }

    private static void insertQuestions() throws SQLException {
        String query1 = "INSERT INTO questions ( QUESTION, ANSWER ) VALUES ( 'Will a giant meteor hit?', 'Fingers crossed' )";
        String query2 = "INSERT INTO questions ( QUESTION, ANSWER ) VALUES ( 'Last forever?', 'November Rain' )";
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query1);
            stmt.executeUpdate(query2);
        }
    }

    public static void initializeDatabase() {
        ds = createDataSource();
        try {
            createQuestionsTable();
            insertQuestions();
        } catch (SQLException e) {
            logger.severe("Database initialization failed: " + e.getMessage());
            System.exit(1);
        }
    }
}