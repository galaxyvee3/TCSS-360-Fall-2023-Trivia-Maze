package view;

import java.util.List;
import java.util.logging.Logger;

/**
 * Class for multiple choice questions.
 * @author Rick Adams.
 * @version Fall 2023
 * Trivia Maze - Team 2.
 */
public class MultipleChoiceQuestions extends Question {
    //======================Constants====================//
    /** Logger constant. **/
    private static final Logger LOGGER = Logger.getLogger(QuestionAnswer.class.getName());

    //private final List <String> myCorrectAnswers;

    //======================Fields======================//
    private String myChoiceA;
    private String myChoiceB;
    private String myChoiceC;

    /**
     * Public constructor for object instantiation.
     * @param theQuestionText Question text in string.
     * @param theAnswerText Answer text in string.
     */
    public MultipleChoiceQuestions(final String theQuestionText, final String theAnswerText,
               final String theChoiceA, final String theChoiceB, final String theChoiceC) {
        super(theQuestionText, theAnswerText);
        setMyType(Type.MULTIPLE_CHOICE);
        myChoiceA = theChoiceA;
        myChoiceB = theChoiceB;
        myChoiceC = theChoiceC;
        //myCorrectAnswers = QuestionAnswer.getAnswers();
        //validateAnswer(myCorrectAnswers, theAnswerText);
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
