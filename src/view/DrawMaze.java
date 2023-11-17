package view;

import controller.Maze;
import model.Door;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Class creates the visual representation of the Trivia Maze with the rooms and doors.
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class DrawMaze extends JPanel implements PropertyChangeListener {
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
    public DrawMaze(final Maze theMaze) {
        super();
        myMaze = theMaze;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(MAZE_SIZE * 20, MAZE_SIZE * 20));
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
                g2d.fillRect(cols * ROOM_SIZE, rows * ROOM_SIZE, ROOM_SIZE, ROOM_SIZE);
            }
        }

        // draw doors
        g2d.setPaint(new Color(100,75,50));

        // for vertical doors
        int x = 0;
        for (Door[] row : myMaze.getVertDoors()) {
            int y = 0;
            for (Door door : row) {
                if (door.getUnlocked()) { // paint door green if unlocked
                    g2d.setPaint(Color.GREEN);
                } else if (door.getClosed()) { // paint door red if closed
                    g2d.setPaint(Color.RED);
                }
                g2d.fillRect((x + 1) * ROOM_SIZE - 5, y * ROOM_SIZE + 15, DOOR_SIZE/3, DOOR_SIZE);
                g2d.setPaint(new Color(100,75,50)); // reset paint to brown
                y++;
            }
            x++;
        }

        // for horizontal doors
        int a = 0;
        for (Door[] col : myMaze.getHorzDoors()) {
            int b = 0;
            for (Door door : col) {
                if (door.getUnlocked()) { // paint door green if unlocked
                    g2d.setPaint(Color.GREEN);
                } else if (door.getClosed()) { // paint door red if closed
                    g2d.setPaint(Color.RED);
                }
                g2d.fillRect(a * ROOM_SIZE + 15, (b + 1) * ROOM_SIZE - 5, DOOR_SIZE, DOOR_SIZE/3);
                g2d.setPaint(new Color(100,75,50)); // reset paint to brown
                b++;
            }
            a++;
        }

        /*
        // for vertical doors
        for (int rows = 0; rows < MAZE_SIZE; rows++) {
            for (int cols = 1; cols < MAZE_SIZE; cols++) {
                g2d.fillRect(cols * ROOM_SIZE - 5, rows * ROOM_SIZE + 15, DOOR_SIZE/3, DOOR_SIZE);
            }

        // for horizontal doors
        for (int rows = 1; rows < MAZE_SIZE; rows++) {
            for (int cols = 0; cols < MAZE_SIZE; cols++) {
                g2d.fillRect(cols * ROOM_SIZE + 15, rows * ROOM_SIZE - 5, DOOR_SIZE, DOOR_SIZE/3);
            }
        }
        }*/

        // draw user current location
        g2d.setPaint(Color.PINK);
        g2d.fillOval(curRow * ROOM_SIZE + 15, curCol * ROOM_SIZE + 15, DOOR_SIZE, DOOR_SIZE);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        repaint();
    }
}