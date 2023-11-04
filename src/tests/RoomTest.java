package tests;

import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoomTest {
    private Room myRoom;


    @BeforeEach
    void setUp() {
        myRoom = new Room();
    }

    @Test
    public void testAnswerTriviaQuestionCorrectly() {
        myRoom.answerTriviaQuestion("Your correct answer");
        assertTrue(myRoom.isQuestionAnsweredCorrectly());
    }

    @Test
    void testAnswerTriviaQuestionIncorrectly() {
        myRoom.answerTriviaQuestion("Incorrect answer");
        assertFalse(myRoom.isQuestionAnsweredCorrectly());
    }

    @Test
    void testCluePresence() {
        boolean cluePresent = false;
        // Create multiple instances to check clue presence.
        for (int i = 0; i < 100; i++) {
            Room room = new Room();
            if (room.isCluePresent()) {
                cluePresent = true;
                break; // Exit the loop if a clue is present in any room.
            }
        }
        assertTrue(cluePresent, "At least one room should have a clue.");
    }
}
