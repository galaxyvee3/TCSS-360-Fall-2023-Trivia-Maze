package tests;

import model.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    private Direction direction1;
    private Direction direction2;

    @BeforeEach
    void setUp() {
        direction1 = Direction.NORTH;
        direction2 = Direction.SOUTH;
    }

    @Test
    void name() {
        assertEquals("NORTH", direction1.name());
    }

    @Test
    void ordinal() {
        assertEquals(0, direction1.ordinal());
    }

    @Test
    void testToString() {
        assertEquals("NORTH", direction1.toString());
    }

    @Test
    void testEquals() {
        assertEquals(direction1, direction1);
        assertNotEquals(direction1, direction2);
    }

    @Test
    void testHashCode() {
        assertEquals(direction1.hashCode(), direction1.hashCode());
        assertNotEquals(direction1.hashCode(), direction2.hashCode());
    }

    @Test
    void compareTo() {
        assertTrue(direction1.compareTo(direction2) < 0);
        assertTrue(direction2.compareTo(direction1) > 0);
        assertEquals(0, direction1.compareTo(direction1));
    }

    @Test
    void getDeclaringClass() {
        assertEquals(Direction.class, direction1.getDeclaringClass());
    }
}
