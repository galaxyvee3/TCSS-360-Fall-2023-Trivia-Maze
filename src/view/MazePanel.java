package view;

import java.util.Map;
import model.Direction;
import model.Door;
import model.Maze;
import model.Room;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

/**
 * Class creates the visual representation of the Trivia Maze with the rooms and doors.
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class MazePanel extends JPanel implements PropertyChangeListener {
    /** The maze being drawn. */
    private final Maze myMaze;

    /** Size of the maze. */
    private static final int MAZE_SIZE = 6;

    /** Size of the rooms. */
    private static final int ROOM_SIZE = 60;

    /** Size of the doors. */
    private static final int DOOR_SIZE = 30;

    /**
     * Default constructor.
     * @param theMaze current maze being played
     */
    public MazePanel(final Maze theMaze) {
        super();
        myMaze = theMaze;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, 200));
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        final int curRow = myMaze.getMyCurrentRow();
        final int curCol = myMaze.getMyCurrentCol();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw rooms
        g2d.setPaint(Color.DARK_GRAY);
        for (int rows = 0; rows < MAZE_SIZE; rows++) {
            for (int cols = 0; cols < MAZE_SIZE; cols++) {
                g2d.fillRect(cols * ROOM_SIZE, rows * ROOM_SIZE, ROOM_SIZE, ROOM_SIZE);
            }
        }

        // draw exit room
        g2d.setPaint(Color.LIGHT_GRAY);
        g2d.fillRect(5 * ROOM_SIZE, 5 * ROOM_SIZE, ROOM_SIZE, ROOM_SIZE);

        // draw exit word
        g2d.setPaint(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.ITALIC, 12));
        g2d.drawString("EXIT", 5 * ROOM_SIZE + 15, 5 * ROOM_SIZE + DOOR_SIZE);

        // draw doors
        g2d.setPaint(new Color(100,75,50));
        for(int i = 0; i < MAZE_SIZE; i++) {
            for(int k = 0; k < MAZE_SIZE; k++) {
                Room[][] maze = myMaze.getRooms();
                Room room = maze[i][k];
                Map <Direction, Door> allDoors = room.getAllDoors();
                for (Direction direction : allDoors.keySet()) {
                    Door door = room.getDoor(direction);
                    if (!door.getUnlocked()) { // draw doors
                        if (door.getClosed()) { // black for closed doors
                            g2d.setPaint(Color.BLACK);
                            if (direction.equals(Direction.NORTH)) {
                                g2d.fillRect(k * ROOM_SIZE, i * ROOM_SIZE - 5, ROOM_SIZE, DOOR_SIZE / 3);
                            } else if (direction == Direction.SOUTH) {
                                g2d.fillRect(k * ROOM_SIZE, (i + 1) * ROOM_SIZE - 5, ROOM_SIZE, DOOR_SIZE / 3);
                            } else if (direction == Direction.WEST) {
                                g2d.fillRect(k * ROOM_SIZE - 5, i * ROOM_SIZE, DOOR_SIZE / 3, ROOM_SIZE);
                            } else if (direction == Direction.EAST) {
                                g2d.fillRect((k + 1) * ROOM_SIZE - 5, i * ROOM_SIZE, DOOR_SIZE / 3, ROOM_SIZE);
                            }
                        } else if (door.getAttempting()) {
                            g2d.setPaint(Color.WHITE);
                            if (direction.equals(Direction.NORTH)) {
                                g2d.fillRect(k * ROOM_SIZE + 15, i * ROOM_SIZE - 5, DOOR_SIZE, DOOR_SIZE / 3);
                            } else if (direction == Direction.SOUTH) {
                                g2d.fillRect(k * ROOM_SIZE + 15, (i + 1) * ROOM_SIZE - 5, DOOR_SIZE, DOOR_SIZE / 3);
                            } else if (direction == Direction.WEST) {
                                g2d.fillRect(k * ROOM_SIZE - 5, i * ROOM_SIZE + 15, DOOR_SIZE / 3, DOOR_SIZE);
                            } else if (direction == Direction.EAST) {
                                g2d.fillRect((k + 1) * ROOM_SIZE - 5, i * ROOM_SIZE + 15, DOOR_SIZE / 3, DOOR_SIZE);
                            }
                        } else { // brown for undiscovered doors
                            g2d.setPaint(new Color(100,75,50));
                            if (direction.equals(Direction.NORTH)) {
                                g2d.fillRect(k * ROOM_SIZE + 15, i * ROOM_SIZE - 5, DOOR_SIZE, DOOR_SIZE / 3);
                            } else if (direction == Direction.SOUTH) {
                                g2d.fillRect(k * ROOM_SIZE + 15, (i + 1) * ROOM_SIZE - 5, DOOR_SIZE, DOOR_SIZE / 3);
                            } else if (direction == Direction.WEST) {
                                g2d.fillRect(k * ROOM_SIZE - 5, i * ROOM_SIZE + 15, DOOR_SIZE / 3, DOOR_SIZE);
                            } else if (direction == Direction.EAST) {
                                g2d.fillRect((k + 1) * ROOM_SIZE - 5, i * ROOM_SIZE + 15, DOOR_SIZE / 3, DOOR_SIZE);
                            }
                        }
                    }
                }
            }
        }
        // draw user current location
        g2d.setPaint(Color.BLUE);
        g2d.fillOval(curCol * ROOM_SIZE + 15, curRow * ROOM_SIZE + 15, DOOR_SIZE, DOOR_SIZE);

        // draw border
        g2d.setPaint(Color.BLACK);
        g2d.fillRect(0, 0, 360, 5);
        g2d.fillRect(0, 355, 360, 5);
        g2d.fillRect(0, 0, 5, 360);
        g2d.fillRect(360, 0, 5, 360);
    }

    /**
     * Repaint maze whenever player has moved within the maze and when a door is unlocked or closed.
     *
     * @param theEvent A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equalsIgnoreCase(Maze.PROPERTY_UPDATE_MAZE)) {
            repaint();
        }
    }
}