package view;

import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static model.Maze.PROPERTY_TRIVIA_QUESTION;

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

    /**
     * Public constructor.
     */
    public QuestionPanel(final Maze theMaze) {
        super(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, 180));
        myQuestion = new Question("", ""); // dummy question
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        // repaint panel based off type of question
        String tq = "Trivia Question: " + myQuestion.getQuestion();
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString(tq, 10, 40);

        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        if (myQuestion.getQuestionType().equalsIgnoreCase("MULTIPLE_CHOICE")) {
            MultipleChoiceQuestions question = (MultipleChoiceQuestions) myQuestion;
            String choiceA = "A: " + question.getChoiceA();
            String choiceB = "B: " + question.getChoiceB();
            String choiceC = "C: " + question.getChoiceC();
            g2d.drawString(choiceA, 10, 60);
            g2d.drawString(choiceB, 10, 80);
            g2d.drawString(choiceC, 10, 100);
        } else if (myQuestion.getQuestionType().equalsIgnoreCase("TRUE_FALSE")) {
            TrueFalseQuestions question = (TrueFalseQuestions) myQuestion;
            String tf = "True or False";
            g2d.drawString(tf, 10, 60);
        } else if (myQuestion.getQuestionType().equalsIgnoreCase("SHORT_ANSWER")) {
            ShortAnswerQuestions question = (ShortAnswerQuestions) myQuestion;
            String sa = "Short one word answer";
            g2d.drawString(sa, 10, 60);
        } else { // default questions to prevent null
        }
    }
    public void reset() {
        repaint();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equalsIgnoreCase(PROPERTY_TRIVIA_QUESTION)) {
            Question question = (Question) theEvent.getNewValue();
            myQuestion = question;
            repaint();
        }
    }
}