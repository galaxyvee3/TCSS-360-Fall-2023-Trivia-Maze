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
        myDs.setUrl("jdbc:sqlite:QuestionsDB.db");
        return myDs;
    }
    public static void initializeDatabase() {
        myDs = createDataSource();
    }
}