package view;

/**
 * Abstract class extended to question classes.
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - team 2
 */
public abstract class Question {

    private final String myQuestionTest;

    private final String myAnswerText;

    /**
     * Public constructor for object instantiation.
     * @param theQuestionText Question text in string.
     * @param theAnswerText Answer text in string.
     */
    public Question(final String theQuestionText, final String theAnswerText) {
        this.myQuestionTest = theQuestionText;
        this.myAnswerText = theAnswerText;
    }

    /**
     * Accessor method that returns the question type.
     * @return The question type.
     */
    public abstract String getQuestionType();

    public String getQuestionText() {
        return myQuestionTest;
    }

    public String getAnswerText() {
        return myAnswerText;
    }
}
