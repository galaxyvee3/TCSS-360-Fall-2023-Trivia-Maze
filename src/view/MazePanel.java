package view;

<<<<<<< HEAD
import model.Maze;
=======
import model.Direction;
>>>>>>> bf6f804a851c073b50f7ffc899660d3759d827b0
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
        super.paintComponents(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        final int curRow = myMaze.getMyCurrentRow();
        final int curCol = myMaze.getMyCurrentCol();

        // draw rooms
        g2d.setPaint(Color.DARK_GRAY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int rows = 0; rows < MAZE_SIZE; rows++) {
            for (int cols = 0; cols < MAZE_SIZE; cols++) {
                g2d.fillRect(cols * ROOM_SIZE, rows * ROOM_SIZE, ROOM_SIZE - 1, ROOM_SIZE - 1);
            }
        }

        // draw doors
        g2d.setPaint(new Color(100,75,50));
        for(int i = 0; i < MAZE_SIZE; i++) {
            for(int k = 0; k < MAZE_SIZE; k++) {
                Room[][] maze = myMaze.getRooms();
                Room room = maze[i][k];
                HashMap<Direction, Door> allDoors = room.getAllDoors();
                for (Direction direction : allDoors.keySet()) {
                    Door door = room.getDoor(direction);
                    // TODO: paint doors
                    /*if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                        g2d.fillRect(i * ROOM_SIZE, k * ROOM_SIZE, DOOR_SIZE, DOOR_SIZE / 3);
                    } else {
                        g2d.fillRect(i * ROOM_SIZE, k * ROOM_SIZE, DOOR_SIZE / 3, DOOR_SIZE);
                    }*/
                }
            }
        }

        // draw user current location
        g2d.setPaint(Color.BLUE);
        g2d.fillOval(curCol * ROOM_SIZE + 15, curRow * ROOM_SIZE + 15, DOOR_SIZE, DOOR_SIZE);
    }

    /*
    // draw locked doors which gonna be red
    private void initializeLockedDoors () {
        Random random = new Random();
        int numberOfRedDoors = random.nextInt((MAZE_SIZE * MAZE_SIZE)/2);
        int row = random.nextInt(MAZE_SIZE);
        int column = random.nextInt(MAZE_SIZE);
        myMaze.[row][column].c
    }
    */
    /**
     * Repaint maze whenever player has moved within the maze and when a door is unlocked or closed.
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