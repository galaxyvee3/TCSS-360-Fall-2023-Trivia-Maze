package view;

/**
 * Abstract class extended to question classes.
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public abstract class Question {

    private final String myQuestionText;

    private final String myAnswerText;

    /**
     * Public constructor for object instantiation.
     * @param theQuestionText Question text in string.
     * @param theAnswerText Answer text in string.
     */
    public Question(final String theQuestionText, final String theAnswerText) {
        this.myQuestionText = theQuestionText;
        this.myAnswerText = theAnswerText;
    }

    /**
     * Accessor method that returns the question type.
     * @return The question type.
     */
    public abstract String getQuestionType();

    /**
     * Return the question.
     * @return trivia question
     */
    public String getQuestionText() {
        return myQuestionText;
    }

    /**
     * Return the answer.
     * @return answer to trivia question
     */
    public String getAnswerText() {
        return myAnswerText;
    }
}