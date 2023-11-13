package view;

/**
 * Class for true or false questions.
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class TrueFalseQuestions extends Question{
    /**
     * Public constructor for object instantiation.
     * @param theQuestionText Question text in string.
     * @param theAnswerText Answer text in string.
     */
    public TrueFalseQuestions(final String theQuestionText, final String theAnswerText) {
        super(theQuestionText, theAnswerText);
    }

    /**
     * Accessor method that returns the question type.
     * @return the question type.
     */
    @Override
    public String getQuestionType() {
        return "True/False";
    }
}