package view;

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

    /**
     * Default constructor.
     */
    protected QuestionDatabase() {
    }

    private static SQLiteDataSource createDataSource() {
        myDs = new SQLiteDataSource();
        myDs.setUrl("jdbc:sqlite:QuestionsDB.db");
        return myDs;
    }

    public static void initializeDatabase() {
        myDs = createDataSource();
    }
}