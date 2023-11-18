package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Class creates the visual representation of the Trivia Questions for the game.
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class QuestionPanel extends JPanel implements PropertyChangeListener {

    private final JLabel myLabel;
    public QuestionPanel() {
        super();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, 50));
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
        if ("currentQuestion".equals(theEvent.getPropertyName())) {
            String newQuestion = (String) theEvent.getNewValue();
            setCurrentQuestion(newQuestion);
            repaint(); // Repaint the panel
        }
    }

    public void setCurrentQuestion(String question) {
        myLabel.setText("Trivia Question: " + question);
    }

    public void displayQuestion(Question myQuestion) {
    }

    public void firePropertyChange() {
    }
}
