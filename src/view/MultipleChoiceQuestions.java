package view;

import java.util.List;
import java.util.logging.Logger;

/**
 * Class for multiple choice questions.
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class MultipleChoiceQuestions extends Question {
    //======================Constants======================//
    /** Logger constant. **/
    private static final Logger LOGGER = Logger.getLogger(QuestionAnswer.class.getName());

    private final List <String> myCorrectAnswers;
    /**
     * Public constructor for object instantiation.
     * @param theQuestionText Question text in string.
     * @param theAnswerText Answer text in string.
     */
    public MultipleChoiceQuestions(final String theQuestionText,
                                   final String theAnswerText) {
        super(theQuestionText, theAnswerText);
        myCorrectAnswers = QuestionAnswer.getAnswers();
        validateAnswer(myCorrectAnswers, theAnswerText);
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
    protected void validateAnswer(final List<String> theCorrectAnswer, final String theUserAnswer) {
        if (theCorrectAnswer.size() == 1 && theCorrectAnswer.contains(theUserAnswer)) {
            // Log a message indicating a correct answer
            LOGGER.info("Success: " + theUserAnswer);
        } else {
            // Log a message indicating an invalid answer
            LOGGER.warning("Invalid answer: " + theUserAnswer);
        }
    }
}
