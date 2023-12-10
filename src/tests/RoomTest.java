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
        HashMap<Direction, Door> map = new HashMap<>();
        map.put(Direction.NORTH, door);
        assertEquals(map, myRoom.getAllDoors());
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
}