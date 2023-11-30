package view;

/**
 * Class for true/false questions.
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
        authenticateAnswer(Boolean.parseBoolean(theAnswerText));
    }
    /**
     * Private constructor for object instantiation using a boolean.
     * @param theQuestionText Question text in string.
     * @param theCorrect Boolean indicating the correct answer.
     */
    private TrueFalseQuestions(final String theQuestionText, final boolean theCorrect) {
        super(theQuestionText, String.valueOf(theCorrect));
        Boolean myAnswer = theCorrect;
    }
    /**
     * Accessor method that returns the question type.
     * @return the question type.
     */
    @Override
    public String getQuestionType() {
        return "true/false";
    }
    /**
     * Check if the provided answer is correct.
     * @param theAnswer The user's answer as a boolean (true/false).
     * @return true if the answer is correct, false otherwise.
     */
    public boolean authenticateAnswer(final boolean theAnswer) {
        return theAnswer == Boolean.parseBoolean(getAnswer());
    }
}