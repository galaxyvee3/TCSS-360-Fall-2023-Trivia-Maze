package view;

public class ShortAnswerQuestions extends Question {

    /**
     * Public constructor for object instantiation.
     *
     * @param theQuestionText Question text in string.
     * @param theAnswerText   Answer text in string.
     */
    public ShortAnswerQuestions(String theQuestionText, String theAnswerText) {
        super(theQuestionText, theAnswerText);
    }

    /**
     * Accessor method that returns the question type.
     * @return The question type.
     */
    @Override
    public String getQuestionType() {
        return null;
    }
}
