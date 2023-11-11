package view;

import controller.Maze;
import model.Room;

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

    /* The size of the rectangles when painting rooms. */
    private static final int RECT_SIZE = 50;

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

        g2d.setPaint(Color.BLACK);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int rows = 0; rows < MAZE_SIZE; rows++) {
            for (int cols = 0; cols < MAZE_SIZE; cols++) {
                g2d.drawRect(cols * RECT_SIZE, rows * RECT_SIZE, RECT_SIZE, RECT_SIZE);
            }
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        repaint();
    }
}