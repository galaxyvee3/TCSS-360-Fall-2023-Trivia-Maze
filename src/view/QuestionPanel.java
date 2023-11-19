package view;

import controller.Maze;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.GameEngine;

/**
 * Class creates the visual representation of the Trivia Questions for the game.
 * @author Viktoria Dolojan
 * @author rick_adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class QuestionPanel extends JPanel implements PropertyChangeListener {

    private final PropertyChangeSupport propertyChangeSupport;

    private final Maze myMaze;
    private final JLabel myLabel;

    /**
     * Public constructor.
     */
    public QuestionPanel() {
        super();
        propertyChangeSupport = new PropertyChangeSupport(this);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, 50));
        myLabel = new JLabel("Trivia Question: ");
        myMaze = new Maze();
        add(myLabel);

        myMaze.addPropertyChangeListener(this);
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponents(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
    }

    /**
     * Repaint question whenever a new trivia question is encountered.
     * @param theEvent A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("myQuestion".equals(theEvent.getPropertyName())) {
            String newQuestion = (String) theEvent.getNewValue();
            setCurrentQuestion(newQuestion);
            repaint(); // Repaint the panel
        }
    }

    public void setCurrentQuestion(final String question) {
        myLabel.setText("Trivia Question: " + question);
    }

    public void displayQuestion(final Question myQuestion) {
    }

//    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
//
//        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
//    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
