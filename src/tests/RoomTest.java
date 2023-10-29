package tests;

import Model.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(false, myRoom.getIsDoorLocked());
        assertEquals(true, myRoom.getIsQuestionAnsweredCorrectly());
    }

    @Test
    void testIncorrectAnswer() {
        myRoom.answerTriviaQuestion("Incorrect answer");
        assertEquals(true, myRoom.getIsDoorLocked());
        assertEquals(false, myRoom.getIsQuestionAnsweredCorrectly());
    }
}