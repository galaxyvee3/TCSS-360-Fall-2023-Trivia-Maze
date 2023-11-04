package tests;

import model.ClueManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClueManagerTest {
    private ClueManager clueManager;

    private String myFilePath;
    private String myExpected;

    @BeforeEach
    void setUp() {
        myFilePath = "Trivia_Maze/ClueTest.txt";
        myExpected = """    
                            Clue 1: This
                            Clue 2: Is
                            Clue 3: Just
                            Clue 4: A
                            Clue 5: Test
                """;
        clueManager = new ClueManager(myFilePath);
    }

    @Test
    public void loadFileTest() {
        assertEquals(myExpected, clueManager.getClues());
    }
}