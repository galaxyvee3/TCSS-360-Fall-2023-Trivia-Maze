package Tests;

import model.Room;
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
        assertTrue(myRoom.getDoorLockStatus());
        assertFalse(myRoom.getQuestionAnsweredCorrectly());
    }

    @Test
    public void testDoorLocks() {
        myRoom.answerTriviaQuestion("Your correct answer");
        assertEquals(false, myRoom.getDoorLockStatus());
        assertEquals(true, myRoom.getQuestionAnsweredCorrectly());
    }

    @Test
    void testIncorrectAnswer() {
        myRoom.answerTriviaQuestion("Incorrect answer");
        assertEquals(true, myRoom.getDoorLockStatus());
        assertEquals(false, myRoom.getQuestionAnsweredCorrectly());
    }
}