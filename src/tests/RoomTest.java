package tests;

import model.Direction;
import model.Door;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    private Room myRoom;

    @BeforeEach
    void setUp() {
        myRoom = new Room();
    }

    @Test
    void testRoomInit() {
        assertEquals(new HashMap<Direction, Door>(), myRoom.getAllDoors());
        assertEquals("Room init", myRoom.toString());
    }

    @Test
    void testAddDoorToRoom() {
        Room room = new Room();
        Door door = new Door(myRoom, room, Direction.SOUTH, Direction.NORTH);
        HashMap<Direction, Door> map = new HashMap<Direction, Door>();
        map.put(Direction.NORTH, door);
        assertEquals(map, myRoom.getAllDoors());
    }

    @Test
    void testAnswerTriviaQuestionCorrectly() {
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

    @Test
    void testDoorQuestionAnswer() {
        Door door = new Door(new Room(), new Room(), Direction.SOUTH, Direction.NORTH);
        door.setQuestion("What are the 4 Pillars of OOP?");
        door.setAnswer("Abstraction, Encapsulation, Inheritance, and Polymorphism");
        assertEquals("What are the 4 Pillars of OOP?", myRoom.retrieveQuestion(door));
        assertEquals("Abstraction, Encapsulation, Inheritance, and Polymorphism", myRoom.retrieveAnswer(door));
    }
}