package view;

import model.Maze;

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

    /** Keeps track of the type of trivia question being presented. */
    private String myQuestionType = "";

    /**
     * Public constructor.
     */
    public QuestionPanel() {
        super(new GridLayout(4, 1));
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

        System.out.println("INIT QUESTION REPAINT"); // for testing purposes

        // repaint panel based off type of question
        if (myQuestionType.equalsIgnoreCase("multiple choice")) {
            remove(1); // remove previous answer options

        } else if (myQuestionType.equalsIgnoreCase("short answer")) {
            remove(1); // remove previous answer options

        } else if (myQuestionType.equalsIgnoreCase("true/false")) {
            remove(1); // remove previous answer options

        }
    }

    public void multipleChoice() {
        final JRadioButtonMenuItem aButton = new JRadioButtonMenuItem("A");
        final JRadioButtonMenuItem bButton = new JRadioButtonMenuItem("B");
        final JRadioButtonMenuItem cButton = new JRadioButtonMenuItem("C");
        add(aButton);
        add(bButton);
        add(cButton);
        aButton.addActionListener(e -> {        });
        bButton.addActionListener(e -> {        });
        cButton.addActionListener(e -> {        });
    }

    public void shortAnswer() {

    }

    public void trueFalse() {
        final JRadioButtonMenuItem trueButton = new JRadioButtonMenuItem("True");
        final JRadioButtonMenuItem falseButton = new JRadioButtonMenuItem("False");
        add(trueButton);
        add(falseButton);
        trueButton.addActionListener(e -> {        });
        falseButton.addActionListener(e -> {        });
    }

    /**
     * Repaint question whenever a new trivia question is encountered.
     * @param theEvent A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        /*if ("myQuestion".equals(theEvent.getPropertyName())) {
            String newQuestion = (String) theEvent.getNewValue();
            myQuestionType = setQuestion(newQuestion);
            //setCurrentQuestion(newQuestion);
            repaint(); // Repaint the panel
        }*/
        // TODO: QUESTIONS ARE NULL
        if (theEvent.getPropertyName().equalsIgnoreCase(Maze.PROPERTY_TRIVIA_QUESTION)) {
            String newQuestion = (String) theEvent.getNewValue();
            myQuestionType = setQuestion(newQuestion);
            //setCurrentQuestion(newQuestion);
            repaint();
        }
    }

    /**
     * Set the current trivia question.
     * @param theQuestion current trivia question
     * @return String type of trivia question
     */
    public String setQuestion(final String theQuestion) {
        final String questionType = theQuestion.toString();
        myLabel.setText("Trivia Question: " + theQuestion);
        repaint(); // Repaint the panel
        return questionType;
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