package tests;

import model.ClueManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClueManagerTest {
    private ClueManager clueManager;
    private String myFilePath;
    private String myExpected;

    @BeforeEach
    void setUp() {
        myFilePath = "ClueTest.txt";
        myExpected = """
        Clue 1: This
        Clue 2: Is
        Clue 3: Just
        Clue 4: A
        Clue 5: Test""";
        clueManager = new ClueManager();
    }

    @Test
    public void loadFileTest() {
        String actualContent;
        try {
            actualContent = String.join("\n", Files.readAllLines(Paths.get(myFilePath)));
        } catch (IOException e) {
            actualContent = "";
        }
        assertEquals(myExpected, actualContent);
    }
}
