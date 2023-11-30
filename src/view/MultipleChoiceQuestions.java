package view;

import java.util.regex.Pattern;

/**
 * Class for multiple choice questions.
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class MultipleChoiceQuestions extends Question {


    /**
     * Public constructor for object instantiation.
     * @param theQuestionText Question text in string.
     * @param theAnswerText Answer text in string.
     */
    public MultipleChoiceQuestions(final String theQuestionText,
                                   final String theAnswerText) {
        super(theQuestionText, theAnswerText);
        validateAnswer();
    }

    /**
     * Accessor method that returns the question type.
     * @return the question type.
     */
    @Override
    public String getQuestionType() {
        return "multiple choice";
    }
    /**
     * Validate that the answer is a single uppercase letter.
     */
    private void validateAnswer() {
        final Pattern letterPattern = Pattern.compile("^[A-Z]$");
        if (!letterPattern.matcher(getAnswer()).matches()) {
            throw new IllegalArgumentException("Invalid answer format : "
                                               + getAnswer());
        }
    }
}
