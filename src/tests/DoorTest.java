package tests;

import model.Direction;
import model.Door;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.MultipleChoiceQuestions;

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
        myDoor = new Door(myRoom1, myRoom2, Direction.NORTH, Direction.SOUTH);
    }

    @Test
    void testDoorInit() {
        assertEquals(myRoom1, myDoor.getRoom1());
        assertEquals(myRoom2, myDoor.getRoom2());
        assertEquals(Direction.NORTH, myDoor.getMyDir1());
        assertEquals(Direction.SOUTH, myDoor.getMyDir2());
        assertFalse(myDoor.getUnlocked());
        assertFalse(myDoor.getClosed());
        assertNull(myDoor.getQuestion());
        assertNull(myDoor.getAnswer());
        assertEquals("Door init", myDoor.toString());
    }

    @Test
    void testSetQuestionAnswer() {
        myDoor.setQuestion("What are the 4 Pillars of OOP?");
        myDoor.setAnswer("Abstraction, Encapsulation, Inheritance, and Polymorphism");
        assertEquals("What are the 4 Pillars of OOP?", myDoor.getQuestion());
        assertEquals("Abstraction, Encapsulation, Inheritance, and Polymorphism", myDoor.getAnswer());
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

        MultipleChoiceQuestions sampleQuestion = new MultipleChoiceQuestions("What is the capital of France?",
                "A");
        myDoor.setAssociatedQuestion(sampleQuestion);
        System.out.println(sampleQuestion.getType());

        assertEquals(sampleQuestion, myDoor.getAssociatedQuestion());
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
        Door door = new Door(myRoom1, myRoom2, Direction.NORTH, Direction.SOUTH);
        PropertyChangeEvent event = new PropertyChangeEvent(this,
                "myQuestion", null,
                "New Question");

        door.propertyChange(event);

    }
}