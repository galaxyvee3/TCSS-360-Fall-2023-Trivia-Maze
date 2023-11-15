package view;

import controller.Maze;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Class creates the visual representation of the Trivia Maze with the rooms and doors.
 * @author Viktoria Dolojan
 */
public class DrawMaze extends JPanel implements PropertyChangeListener {
    /* The maze being drawn. */
    private Maze myMaze = null;

    /* The size of the maze. */
    private static final int MAZE_SIZE = 6;

    /* The size of the rooms. */
    private static final int ROOM_SIZE = 60;

    /* The size of the doors. */
    private static final int DOOR_SIZE = 30;

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
        for (int rows = 0; rows < MAZE_SIZE; rows++) {
            for (int cols = 1; cols < MAZE_SIZE; cols++) {
                g2d.fillRect(cols * ROOM_SIZE - 5, rows * ROOM_SIZE + 15, DOOR_SIZE/3, DOOR_SIZE);
            }
        }
        // for horizontal doors
        for (int rows = 1; rows < MAZE_SIZE; rows++) {
            for (int cols = 0; cols < MAZE_SIZE; cols++) {
                g2d.fillRect(cols * ROOM_SIZE + 15, rows * ROOM_SIZE - 5, DOOR_SIZE, DOOR_SIZE/3);
            }
        }

        // draw user current location
        g2d.setPaint(Color.PINK);
        g2d.fillOval(curRow * ROOM_SIZE + 15, curCol * ROOM_SIZE + 15, DOOR_SIZE, DOOR_SIZE);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        repaint();
    }
}