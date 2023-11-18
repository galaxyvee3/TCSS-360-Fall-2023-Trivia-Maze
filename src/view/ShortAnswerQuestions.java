package view;

import java.util.logging.Logger;
import java.util.regex.Pattern;



/**
 * Class for short answer questions.
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class ShortAnswerQuestions extends Question {

    private static final Logger LOGGER = Logger.getLogger(ShortAnswerQuestions.class.getName());
    /**
     * Public constructor for object instantiation.
     * @param theQuestionText Question text in string.
     * @param theAnswerText Answer text in string.
     */
    public ShortAnswerQuestions(final String theQuestionText,
                                final String theAnswerText) {
        super(theQuestionText, theAnswerText);
        authenticateAnswerWithRegex(theAnswerText);
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
     * Authenticates the user's answer for short answer questions using regular expressions.
     *
     * @param theAnswer The user's answer to be authenticated.
     */
    public void authenticateAnswerWithRegex(final String theAnswer) {
        final String regexPattern = "(?i)four|4";
        final Pattern pattern = Pattern.compile(regexPattern);

        final boolean matches = pattern.matcher(theAnswer).matches();

        if (!matches) {
            // Log a message indicating an invalid answer
            LOGGER.warning("Invalid answer format: " + theAnswer);
        }
    }
}