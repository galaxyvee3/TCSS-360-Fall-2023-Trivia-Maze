package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoorTest {
    private Door myDoor;
    private Room myRoom1;
    private Room myRoom2;

    @BeforeEach
    void setUp() {
        myRoom1 = new Room(0, 0);
        myRoom2 = new Room(1, 0);
        myDoor = new Door(myRoom1, myRoom2, Direction.SOUTH, Direction.NORTH);
    }

    @Test
    void testDoorInit() {
        assertEquals(myRoom1, myDoor.getRoom1());
        assertEquals(myRoom2, myDoor.getRoom2());
        assertEquals(Direction.SOUTH, myDoor.getMyDir1());
        assertEquals(Direction.NORTH, myDoor.getMyDir2());
        assertFalse(myDoor.getUnlocked());
        assertFalse(myDoor.getClosed());
        assertTrue(myDoor.getQuestion().getQuestionType().equalsIgnoreCase("DEFAULT"));
        assertFalse(myDoor.getAttempting());
    }

    @Test
    void testUnlockDoor() {
        assertFalse(myDoor.getUnlocked()); // initially locked
        myDoor.unlockDoor();
        assertTrue(myDoor.getUnlocked()); // now unlocked
    }

    @Test
    void testCloseDoor() {
        assertFalse(myDoor.getClosed()); // initially closed
        myDoor.closeDoor();
        assertTrue(myDoor.getClosed()); // now open
    }

    @Test
    void testAttachMultipleChoice() {
        myDoor.setQuestion(new MultipleChoiceQuestions("", "", "", "", ""));
        assertTrue(myDoor.getQuestion().getQuestionType().equalsIgnoreCase("MULTIPLE_CHOICE"));
    }

    @Test
    void testAttachTrueFalse() {
        myDoor.setQuestion(new TrueFalseQuestions("", ""));
        assertTrue(myDoor.getQuestion().getQuestionType().equalsIgnoreCase("TRUE_FALSE"));
    }

    @Test
    void testAttachShortAnswer() {
        myDoor.setQuestion(new ShortAnswerQuestions("", ""));
        assertTrue(myDoor.getQuestion().getQuestionType().equalsIgnoreCase("SHORT_ANSWER"));
    }

    @Test
    void testAttemptDoor() {
        assertFalse(myDoor.getAttempting()); // initially false
        myDoor.setAttempting(true);
        assertTrue(myDoor.getAttempting()); // now true
    }
}