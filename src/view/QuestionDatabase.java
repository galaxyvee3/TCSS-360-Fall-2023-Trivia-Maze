package view;

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
    private static final Logger LOGGER = Logger.getLogger(QuestionDatabase.class.getName());
    /** Path for SQLite database. **/
    private static final String URL = "jdbc:sqlite:QuestionsDB.db";
//=====================Fields==========================//
    /** Data source object. **/
    private static SQLiteDataSource myDs = null;

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
    private static synchronized SQLiteDataSource createDataSource() {
        if (myDs == null) {
            myDs = new SQLiteDataSource();
            myDs.setUrl(URL);
        }
        return myDs;
    }

    /**
     * Helper method to initialize the database.
     */
    public static synchronized void initializeDatabase() {
        try {
            myDs = createDataSource();
        } catch (Exception e) {
            LOGGER.severe("Database initialization has failed" + e);
        }
    }

    /**
     * Data source accessor method.
     * @return returns public data source.
     */
    public static synchronized SQLiteDataSource getDataSource() {
        if (myDs == null) {
            initializeDatabase();
        }
        return myDs;
    }
}
