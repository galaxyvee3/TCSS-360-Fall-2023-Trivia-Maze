package view;

import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Class creates the visual representation of the Trivia Questions for the game.
 * @author Viktoria Dolojan
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class QuestionPanel extends JPanel implements PropertyChangeListener {
    /** Keeps track of the trivia question being presented. */
    private Question myQuestion;

    /** JLabel to display trivia question. */
    private JLabel myLabel;

    /** JPanel to prompt the player for an answer. */
    private JPanel myPanel;

    /**
     * Public constructor.
     */
    public QuestionPanel() {
        super(new GridLayout(2, 1));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, 150));
        myLabel = new JLabel("Trivia Question: ");
        myPanel = promptUser();
        add(myLabel);
        add(myPanel);
    }

    /**
     * Prompt the player for an answer.
     * @return JPanel
     */
    public JPanel promptUser() {
        // TODO: UPDATE PANEL BASED ON TYPE OF QUESTION
        if (myQuestion != null) {
            if (myQuestion.getQuestionType().equalsIgnoreCase("multiple choice")) {
                System.out.println("mc");
            } else if (myQuestion.getQuestionType().equalsIgnoreCase("short answer")) {
                System.out.println("sa");
            } else if (myQuestion.getQuestionType().equalsIgnoreCase("true/false")) {
                System.out.println("tf");
            }
        }
        JPanel panel = new JPanel(new GridLayout(4,1));
        JLabel label = new JLabel("Answer");
        JLabel result = new JLabel("");

        JTextField input = new JTextField();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = input.getText();
                // TODO: UPDATE TEXT TO SHOW PLAYER IF THEY WERE CORRECT OR NOT
                result.setText(userInput);
            }
        });
        panel.add(label);
        panel.add(input);
        panel.add(submitButton);
        panel.add(result);
        return panel;
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponents(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        System.out.println("INIT QUESTION REPAINT"); // for testing purposes

        // repaint panel based off type of question
        promptUser();
        remove(1); // remove previous answer options
        //myPanel = promptUser(); // update player answer panel
        add(myPanel);
    }

    /**
     * Sets the current trivia question to be displayed.
     * @param theQuestion current trivia question
     */
    public void setCurrentQuestion(final String theQuestion) {
        myLabel.setText("Trivia Question: " + theQuestion);
    }

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

    /**
     * Repaint question whenever a new trivia question is encountered.
     * @param theEvent A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        // TODO: QUESTIONS ARE NULL
        if (theEvent.getPropertyName().equalsIgnoreCase(Maze.PROPERTY_TRIVIA_QUESTION)) {
            Question newQuestion = (Question) theEvent.getNewValue();
            myQuestion = newQuestion;
            repaint();
        }
    }
}