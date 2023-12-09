package view;

/**
 * Class for multiple choice questions.
 * @author Rick Adams
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2.
 */
public class MultipleChoiceQuestions extends Question {
    /** The first choice for the question. */
    private final String myChoiceA;

    /** The second choice for the question. */
    private final String myChoiceB;

    /** The third choice for the question. */
    private final String myChoiceC;

    /**
     * Public constructor for object instantiation.
     * @param theQuestion Question text in string
     * @param theAnswer Answer text in string
     */
    public MultipleChoiceQuestions(final String theQuestion, final String theAnswer,
               final String theChoiceA, final String theChoiceB, final String theChoiceC) {
        super(theQuestion, theAnswer);
        setMyType(Type.MULTIPLE_CHOICE);
        myChoiceA = theChoiceA;
        myChoiceB = theChoiceB;
        myChoiceC = theChoiceC;
    }

    /**
     * Get the first choice for the trivia question.
     * @return choice A
     */
    public String getChoiceA() {
        return myChoiceA;
    }

    /**
     * Get the second choice for the trivia question.
     * @return choice B
     */
    public String getChoiceB() {
        return myChoiceB;
    }

    /**
     * Get the third choice for the trivia question.
     * @return choice C
     */
    public String getChoiceC() {
        return myChoiceC;
    }
}