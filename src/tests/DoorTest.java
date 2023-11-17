package tests;

import model.Door;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoorTest {
    @Test
    void testDoorInit() {
        Door door = new Door();
        assertFalse(door.getUnlocked());
        assertFalse(door.getClosed());
        assertNull(door.getQuestion());
        assertNull(door.getAnswer());
        assertEquals("Door init", door.toString());
    }

    @Test
    void testSetQuestionAnswer() {
        Door door = new Door();
        door.setQuestion("What are the 4 Pillars of OOP?");
        door.setAnswer("Abstraction, Encapsulation, Inheritance, and Polymorphism");
        assertEquals("What are the 4 Pillars of OOP?", door.getQuestion());
        assertEquals("Abstraction, Encapsulation, Inheritance, and Polymorphism", door.getAnswer());
    }

    @Test
    void testUnlockedDoor() {
        Door door = new Door();
        assertFalse(door.getUnlocked());
        door.unlockDoor();
        assertTrue(door.getUnlocked());
    }

    @Test
    void testClosedDoor() {
        Door door = new Door();
        assertFalse(door.getClosed());
        door.closeDoor();
        assertTrue(door.getClosed());
    }
}