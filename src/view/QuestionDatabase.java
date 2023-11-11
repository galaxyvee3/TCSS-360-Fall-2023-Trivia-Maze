package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import org.sqlite.SQLiteDataSource;

public class QuestionDatabase {
    private static final Logger LOGGER = Logger.getLogger(QuestionDatabase.class.getName());

    private static SQLiteDataSource myDs = null;

    protected QuestionDatabase() {
    }
    private static SQLiteDataSource  createDataSource() {
        myDs = new SQLiteDataSource();
        myDs.setUrl("jdbc:sqlite:questions.db");
        return myDs;
    }
    private static void createQuestionsTable() throws SQLException {
        final String query = "CREATE TABLE IF NOT EXISTS questions ( "
                       + "QUESTION TEXT NOT NULL, " + "ANSWER TEXT NOT NULL )";

        try (final Connection conn = myDs.getConnection();
             Statement stmt = conn.createStatement()) {
             stmt.executeUpdate(query);
        }
    }

//    private static void insertQuestions() throws SQLException {
//        String query1 = "INSERT INTO questions ( QUESTION, ANSWER ) VALUES ( 'Will a giant meteor hit?', 'Fingers crossed' )";
//        String query2 = "INSERT INTO questions ( QUESTION, ANSWER ) VALUES ( 'Last forever?', 'November Rain' )";
//        try (final Connection conn = myDs.getConnection();
//             final Statement stmt = conn.createStatement()) {
//            stmt.executeUpdate(query1);
//            stmt.executeUpdate(query2);
//        }
//    }

    public static void initializeDatabase() {
        myDs = createDataSource();
        try {
            createQuestionsTable();
//            insertQuestions();
        } catch (final SQLException theE) {
            LOGGER.severe("Database initialization failed: " + theE.getMessage());
            System.exit(1);
        }
    }
}