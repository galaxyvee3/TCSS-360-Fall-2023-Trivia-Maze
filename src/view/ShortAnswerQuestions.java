package view;

import java.io.Serializable;

/**
 * Class for short answer questions.
 * @author Rick Adams
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class ShortAnswerQuestions extends Question implements Serializable {
    /**
     * Public constructor for object instantiation.
     * @param theQuestion Question text in string.
     * @param theAnswer Answer text in string.
     */
    public ShortAnswerQuestions(final String theQuestion, final String theAnswer) {
        super(theQuestion, theAnswer);
        setMyType(Type.SHORT_ANSWER);
    }
}