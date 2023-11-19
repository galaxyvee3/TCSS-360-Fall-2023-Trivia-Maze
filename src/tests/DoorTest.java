package tests;

import java.beans.PropertyChangeEvent;
import model.Door;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.MultipleChoiceQuestions;
import view.Question;
import static org.junit.jupiter.api.Assertions.*;

public class DoorTest {

    private Door myDoor;

    @BeforeEach
    void setUp() {
        myDoor = new Door();
    }

    @Test
    void testDoorInit() {

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
        Door door = new Door();
        PropertyChangeEvent event = new PropertyChangeEvent(this,
                "myQuestion", null,
                "New Question");

        door.propertyChange(event);

    }
}