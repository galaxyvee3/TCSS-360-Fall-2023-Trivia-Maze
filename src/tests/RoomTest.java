package tests;


import model.Direction;
import model.Door;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoomTest {

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(1, 2);
    }

    @Test
    void getAllDoors() {
        assertNotNull(room.getAllDoors());
        assertTrue(room.getAllDoors().isEmpty());
    }

    @Test
    void getRow() {
        assertEquals(1, room.getRow());
    }

    @Test
    void getColumn() {
        assertEquals(2, room.getColumn());
    }

    @Test
    void getDoor() {
        Direction north = Direction.NORTH; // Replace with your actual Direction implementation
        Room otherRoom = new Room(2, 2); // Create another room for the door

        Door door = new Door(room, otherRoom, Direction.EAST, Direction.WEST);
        room.addDoor(north, door);

        assertNotNull(room.getDoor(north));
        assertEquals(door, room.getDoor(north));
    }

    @Test
    void addDoor() {
        Direction east = Direction.EAST; // Replace with your actual Direction implementation
        Room otherRoom = new Room(1, 3); // Create another room for the door

        Door door = new Door(room, otherRoom, Direction.WEST, Direction.EAST);
        room.addDoor(east, door);

        assertNotNull(room.getDoor(east));
        assertEquals(door, room.getDoor(east));
    }

    @Test
    void testToString() {
        assertEquals("\nRoom: 1, 2", room.toString());
    }
}
