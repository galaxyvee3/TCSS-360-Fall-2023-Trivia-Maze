package view;

public class MultipleChoiceQuestions extends Question {


    /**
     * Public constructor for object instantiation.
     *
     * @param theQuestionText Question text in string.
     * @param theAnswerText   Answer text in string.
     */
    public MultipleChoiceQuestions(final String theQuestionText, final String theAnswerText) {
        super(theQuestionText, theAnswerText);
    }

    /**
     * Accessor method that returns the question type.
     * @return The question type.
     */
    @Override
    public String getQuestionType() {
        return "multiple choice";
    }
}
