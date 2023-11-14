package tests;

import model.Door;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoorTest {
    @Test
    void testDoorInit() {
        Door door = new Door();
        assertFalse(door.getUnlocked());
        assertNull(door.getQuestion());
        assertNull(door.getAnswer());
    }

    @Test
    void testSetQuestionAnswer() {
        Door door = new Door();
        door.setQuestion("What are the 4 Pillars of OOP?");
        door.setAnswer("Abstraction, Encapsulation, Inheritance, and Polymorphism");
        assertEquals("What are the 4 Pillars of OOP?", door.getQuestion());
        assertEquals("Abstraction, Encapsulation, Inheritance, and Polymorphism", door.getAnswer());
    }
}