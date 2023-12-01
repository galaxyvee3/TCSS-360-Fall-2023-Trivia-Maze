package view;

import java.util.List;
import java.util.logging.Logger;

/**
 * Class for short answer questions.
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class ShortAnswerQuestions extends Question {
//===================Constants====================//
    /** Logger constant. */
    private static final Logger LOGGER = Logger.getLogger(ShortAnswerQuestions.class.getName());
//===================Fields======================//

    /** String containing the correct answers. */
    private final List <String> myCorrectAnswers;
    /**
     * Public constructor for object instantiation.
     * @param theQuestionText Question text in string.
     * @param theAnswerText Answer text in string.
     */
    public ShortAnswerQuestions(final String theQuestionText,
                                final String theAnswerText) {
        super(theQuestionText, theAnswerText);
        // Retrieve correct answers from the database
        myCorrectAnswers = QuestionAnswer.getAnswers();
        // Authenticate the provided answer against the correct answers
        authenticateAnswer(myCorrectAnswers, theAnswerText);
    }
    /**
     * Accessor method that returns the question type.
     * @return The question type.
     */
    @Override
    public String getQuestionType() {
        return "short answer";
    }
    /**
     * Authenticate the user's answer for short answer questions.
     * @param theCorrectAnswer List of correct answers for short answer questions.
     * @param theUserAnswer The user's answer to be authenticated.
     */
    protected void authenticateAnswer(final List<String> theCorrectAnswer,
                                    final String theUserAnswer) {

        if (! theCorrectAnswer.contains(theUserAnswer)) {
            // Log a message indicating an invalid answer
            LOGGER.warning("Invalid answer: " + theUserAnswer);
        } else {
            LOGGER.info("Success: " + theUserAnswer);
        }
    }
}
