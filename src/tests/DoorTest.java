package tests;

import model.Direction;
import model.Door;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.*;

public class DoorTest {

    private Door myDoor;

    private Room myRoom1;

    private Room myRoom2;

    @BeforeEach
    void setUp() {
        myRoom1 = new Room();
        myRoom2 = new Room();
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
        assertNull(myDoor.getQuestion());
        assertEquals("Door init", myDoor.toString());
    }

    @Test
    void testUnlockedDoor() {
        assertFalse(myDoor.getUnlocked());
        myDoor.unlockDoor();
        assertTrue(myDoor.getUnlocked());
    }

    @Test
    void testClosedDoor() {
        assertFalse(myDoor.getClosed());
        myDoor.closeDoor();
        assertTrue(myDoor.getClosed());
    }

    @Test
    public void testSetAssociatedQuestion() {

        //MultipleChoiceQuestions sampleQuestion = new MultipleChoiceQuestions("What is the capital of France?",
                //"A");
        //myDoor.setAssociatedQuestion(sampleQuestion);
        //System.out.println(sampleQuestion.getMyType());

        //assertEquals(sampleQuestion, myDoor.getAssociatedQuestion());
    }

    @Test
    public void testUnlockDoor() {

        assertFalse(myDoor.getUnlocked()); // Door should be initially locked

        myDoor.unlockDoor();

        assertTrue(myDoor.getUnlocked()); // Door should be unlocked after calling unlockDoor
    }

    @Test
    public void testCloseDoor() {

        assertFalse(myDoor.getClosed()); // Door should be initially open

        myDoor.closeDoor();

        assertTrue(myDoor.getClosed()); // Door should be closed forever after calling closeDoor
    }

    @Test
    public void testPropertyChange() {
        myDoor = new Door(myRoom1, myRoom2, Direction.SOUTH, Direction.NORTH);
        PropertyChangeEvent event = new PropertyChangeEvent(this,
                "myQuestion", null,
                "New Question");
        myDoor.propertyChange(event);
    }
}