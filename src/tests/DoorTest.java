package tests;

import model.Door;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoorTest {
    @Test
    void testDoorInit() {
        Door door = new Door(0);
        assertEquals(0, door.getDoorNumber());
        assertEquals(false, door.getStatus());
        assertEquals(null, door.getQuestion());
        assertEquals(null, door.getAnswer());
        assertEquals(null, door.getRoom1());
        assertEquals(null, door.getRoom2());
        assertEquals(null, door.getDirection1());
        assertEquals(null, door.getDirection2());
    }

    @Test
    void testSetQuestionAnswer() {
        Door door = new Door(0);
        door.setQuestion("What are the 4 Pillars of OOP?");
        door.setAnswer("Abstraction, Encapsulation, Inheritance, and Polymorphism");
        assertEquals("What are the 4 Pillars of OOP?", door.getQuestion());
        assertEquals("Abstraction, Encapsulation, Inheritance, and Polymorphism", door.getAnswer());
    }
}