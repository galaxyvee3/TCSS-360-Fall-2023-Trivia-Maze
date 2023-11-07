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
    private int myRows = 0;

    private int myColumns = 0;

    public DrawMaze(final Maze theMaze) {
        super();
        myRows = theMaze.getRows();
        myColumns = theMaze.getCols();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(myRows * 20,myColumns * 20));
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponents(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setPaint(Color.BLACK);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int rec = 35;
        for (int rows = 0; rows < myRows; rows++) {
            for (int cols = 0; cols < myColumns; cols++) {
                g2d.drawRect(cols * rec, rows * rec, rec, rec);
            }
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        repaint();
    }
}