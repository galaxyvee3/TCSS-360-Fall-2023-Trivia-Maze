package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Class creates the visual representation of the Trivia Questions for the game.
 * @author Viktoria Dolojan
 * @author rick_adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class QuestionPanel extends JPanel implements PropertyChangeListener {
    /** Property change support for the class. */
    private final PropertyChangeSupport propertyChangeSupport;

    /** JLabel to display trivia question. */
    private JLabel myLabel = null;

    /**
     * Public constructor.
     */
    public QuestionPanel() {
        super();
        propertyChangeSupport = new PropertyChangeSupport(this);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, 150));
        myLabel = new JLabel("Trivia Question: ");
        add(myLabel);
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
            setQuestion(newQuestion);
            //setCurrentQuestion(newQuestion);
            repaint(); // Repaint the panel
        }
    }

    /**
     * Set the current trivia question.
     * @param theQuestion current trivia question
     */
    public void setQuestion(final String theQuestion) {
        myLabel.setText("Trivia Question: " + theQuestion);
        repaint(); // Repaint the panel
    }

//    public void setCurrentQuestion(final String theQuestion) {
//        myLabel.setText("Trivia Question: " + theQuestion);
//    }

    /**
     * Displays the current trivia question.
     * @param theQuestion current trivia question
     */
    public void displayQuestion(final Question theQuestion) {
    }

    /**
     * Sets the current trivia question from the database.
     * @param theQuestion current trivia question
     */
    public void setQuestionFromDatabase(final String theQuestion) {
        myLabel.setText("Trivia Question: " + theQuestion);
        repaint();
    }

//    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
//
//        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
//    }

    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        propertyChangeSupport.addPropertyChangeListener(theListener);
    }

    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        propertyChangeSupport.removePropertyChangeListener(theListener);
    }
}