package view;

/**
 * Class for true/false questions.
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class TrueFalseQuestions extends Question{

    private String myQuestionText;

    private String myAnswerText;

    private Boolean myAnswer;


    /**
     * Public constructor for object instantiation.
     * @param theQuestionText Question text in string.
     * @param theAnswerText Answer text in string.
     */
    public TrueFalseQuestions(final String theQuestionText,
                              final String theAnswerText) {
        super(theQuestionText, theAnswerText);
        this.myQuestionText = theQuestionText;
        this.myAnswerText = theAnswerText;
    }
    /**
     * Private constructor for object instantiation using a boolean.
     * @param theQuestionText Question text in string.
     * @param theCorrect Boolean indicating the correct answer.
     */
    private TrueFalseQuestions(final String theQuestionText,
                               final boolean theCorrect) {
        super(theQuestionText, String.valueOf(theCorrect));
        this.myAnswer = theCorrect;
    }
    /**
     * Accessor method that returns the question type.
     * @return the question type.
     */
    @Override
    public String getQuestionType() {
        return "True/False";
    }
    /**
     * Check if the provided answer is correct.
     * @param userAnswer The user's answer as a boolean (true/false).
     * @return true if the answer is correct, false otherwise.
     */
    public boolean authenticateAnswer(boolean userAnswer) {
        return userAnswer == Boolean.parseBoolean(getAnswer());
    }
}