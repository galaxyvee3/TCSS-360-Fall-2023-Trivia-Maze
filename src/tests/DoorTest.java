package tests;

import Model.Door;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoorTest {
    @Test
    public void testDoorInit() {
        Door door = new Door();
        assertEquals(0, door.getDoorNumber());
        assertFalse(door.getStatus());
        assertEquals(null, door.getRoom1());
        assertEquals(null, door.getRoom2());
        assertEquals(null, door.getDirection1());
        assertEquals(null, door.getDirection2());
    }
}