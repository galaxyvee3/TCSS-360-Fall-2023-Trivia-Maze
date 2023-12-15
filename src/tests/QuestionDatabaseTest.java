package tests;

import model.QuestionDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionDatabaseTest {

    private QuestionDatabase questionDatabase;

    @BeforeEach
    void setUp() {
        // Initialize the QuestionDatabase
        questionDatabase = new QuestionDatabase();
    }

    @Test
    void initializeDatabase() {
        // Test if the database is initialized successfully
        assertNotNull(questionDatabase.initializeDatabase());
    }

    @Test
    void getDataSource() {
        // Test if the data source is not null after initialization
        assertNotNull(questionDatabase.getDataSource());
    }

    @Test
    void dataSourceString() {
        // Test if the data source string is not null after initialization
        assertNotNull(questionDatabase.dataSourceString());
    }
}