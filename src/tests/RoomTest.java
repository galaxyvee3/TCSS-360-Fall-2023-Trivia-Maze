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
        assertTrue(myRoom.getIsDoorLocked());
        assertFalse(myRoom.getIsQuestionAnsweredCorrectly());
    }

    @Test
    public void testDoorLocks() {
        myRoom.answerTriviaQuestion("Your correct answer");
        assertFalse(myRoom.getIsDoorLocked());
        assertTrue(myRoom.getIsQuestionAnsweredCorrectly());
    }

    @Test
    void testIncorrectAnswer() {
        myRoom.answerTriviaQuestion("Incorrect answer");
        assertTrue(myRoom.getIsDoorLocked());
        assertFalse(myRoom.getIsQuestionAnsweredCorrectly());
    }
}