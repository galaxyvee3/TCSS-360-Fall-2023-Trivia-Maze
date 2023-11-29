package view;

import java.util.List;
import java.util.logging.Logger;

/**
 * Class for true/false questions.
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class TrueFalseQuestions extends Question{
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
    public TrueFalseQuestions(final String theQuestionText, final String theAnswerText) {
        super(theQuestionText, theAnswerText);
        myCorrectAnswers = QuestionAnswer.getAnswers();
        authenticateAnswer(myCorrectAnswers, Boolean.parseBoolean(theAnswerText));
    }
    /**
     * Private constructor for object instantiation using a boolean.
     *
     * @param theQuestionText  Question text in string.
     * @param theCorrect       Boolean indicating the correct answer.
     * @param myCorrectAnswers
     */
    private TrueFalseQuestions(final String theQuestionText,
                               final boolean theCorrect, List <String> myCorrectAnswers) {
        super(theQuestionText, String.valueOf(theCorrect));
        this.myCorrectAnswers = myCorrectAnswers;
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
    public void authenticateAnswer(final List<String> theCorrectAnswer,
                                      final boolean theAnswer) {
        if (! theCorrectAnswer.contains(theAnswer)) {
            // Log a message indicating an invalid answer
            LOGGER.warning("Invalid answer: " + theAnswer);
        } else {
            LOGGER.info("Success: " + theAnswer);
        }
    }
}