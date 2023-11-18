package view;

import java.util.regex.Pattern;

/**
 * Class for short answer questions.
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class ShortAnswerQuestions extends Question {
    /**
     * Public constructor for object instantiation.
     * @param theQuestionText Question text in string.
     * @param theAnswerText Answer text in string.
     */
    public ShortAnswerQuestions(final String theQuestionText,
                                final String theAnswerText) {
        super(theQuestionText, theAnswerText);
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
     * @return True if the user's answer matches the pattern, false otherwise.
     */
    public boolean authenticateAnswerWithRegex(final String theAnswer) {
        // Define a regular expression pattern for valid answers
        final String regexPattern = "(?i)four|4";
        final Pattern pattern = Pattern.compile(regexPattern);

        return pattern.matcher(theAnswer).matches();
    }
}