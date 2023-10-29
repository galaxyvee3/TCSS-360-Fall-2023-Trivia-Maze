package tests;

import Model.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoomTest {
    private static Room myRoom;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
       myRoom = new Room();
    }

    @Test
    public void testRoomInit() {
        assertTrue(myRoom.isDoorLocked());
        assertFalse(myRoom.isQuestionAnsweredCorrectly());
    }

    @Test
    public void testDoorLocks() {
        myRoom.answerTriviaQuestion("Your correct answer");
        assertFalse(myRoom.isDoorLocked());
        assertTrue(myRoom.isQuestionAnsweredCorrectly());
    }

    @Test
    void testIncorrectAnswer() {
        myRoom.answerTriviaQuestion( "Incorrect answer");
        assertTrue(myRoom.isDoorLocked());
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