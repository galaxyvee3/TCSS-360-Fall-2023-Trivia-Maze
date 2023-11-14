package view;

import java.util.logging.Level;
import org.sqlite.SQLiteDataSource;

import java.util.logging.Logger;

/**
 * The question database that will be used for the trivia questions in the game.
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class QuestionDatabase {
    private static final Logger LOGGER = Logger.getLogger(QuestionDatabase.class.getName());
    private static SQLiteDataSource myDs = null;

    private QuestionDatabase() {
        // Private constructor to prevent instantiation
    }

    private static synchronized SQLiteDataSource createDataSource() {
        if (myDs == null) {
            myDs = new SQLiteDataSource();
            myDs.setUrl("jdbc:sqlite:QuestionsDB.db");
        }
        return myDs;
    }

    public static synchronized void initializeDatabase() {
        try {
            myDs = createDataSource();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Database initialization failed.", e);
        }
    }

    public static synchronized SQLiteDataSource getDataSource() {
        if (myDs == null) {
            initializeDatabase();
        }
        return myDs;
    }
}
