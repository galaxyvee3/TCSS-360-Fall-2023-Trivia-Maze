package model;

import org.sqlite.SQLiteDataSource;


/**
 * The question database that will be used for the trivia questions in the game.
 *
 * @author Rick Adams.
 * @version Fall 2023.
 * Trivia Maze - Team 2.
 */
public final class QuestionDatabase {
    //======================Constants======================//
    /** Path for SQLite database. **/
    private static final String URL = "jdbc:sqlite:QuestionsDB.db";

    //=====================Fields==========================//
    /** Data source object. **/
    private static SQLiteDataSource myDs;

    /**
     * Default constructor.
     */
    public QuestionDatabase() {
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
    public static SQLiteDataSource initializeDatabase() {
        return myDs = createDataSource();
    }

    /**
     * Data source accessor method.
     * @return returns public data source.
     */
    public static SQLiteDataSource getDataSource() {
        if (myDs == null) {
            initializeDatabase();
        }
        return myDs;
    }

    /**
     * Accessor method for the data source.
     * @return returns the data source as a string.
     */
    public static String dataSourceString() {
        return URL;
    }
}
