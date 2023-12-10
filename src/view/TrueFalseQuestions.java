package view;

/**
 * Class for true/false questions.
 * @author Rick Adams
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class TrueFalseQuestions extends Question{
    /**
     * Public constructor for object instantiation.
     * @param theQuestion Question text in string.
     * @param theAnswer Boolean indicating the correct answer.
     */
    public TrueFalseQuestions(final String theQuestion, final String theAnswer) {
        super(theQuestion, String.valueOf(theAnswer));
        setMyType(Type.TRUE_FALSE);
    }
}