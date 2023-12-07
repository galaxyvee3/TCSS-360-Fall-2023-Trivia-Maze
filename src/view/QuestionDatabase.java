package view;

import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;
import java.util.logging.Logger;

/**
 * The question database that will be used for the trivia questions in the game.
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public final class QuestionDatabase {
    //======================Constants======================//
    /** Logger constant. **/
//    private static final Logger LOGGER = Logger.getLogger(QuestionDatabase.class.getName());
    /** Path for SQLite database. **/
    private static final String URL = "jdbc:sqlite:QuestionsDB.db";
    //=====================Fields==========================//
    /** Data source object. **/
    private static SQLiteDataSource myDs;

    /**
     * Private constructor.
     * No instantiation.
     */
    private QuestionDatabase() {
    }
    /**
     * Helper method that creates data source.
     * @return returns the data source.
     */
    private static SQLiteDataSource createDataSource() {
        synchronized (SQLiteDataSource.class) {
            if (myDs == null) {
                myDs = new SQLiteDataSource();
                myDs.setUrl(URL);
            }
            return myDs;
        }
    }
    /**
     * Helper method to initialize the database.
     */
    public static void initializeDatabase() throws SQLException {
        myDs = createDataSource();
    }

    /**
     * Data source accessor method.
     * @return returns public data source.
     */
    public static SQLiteDataSource getDataSource() throws SQLException {
        if (myDs == null) {
            initializeDatabase();
        }
        return myDs;
    }
}
