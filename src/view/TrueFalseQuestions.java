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
     * @param theCorrect Boolean indicating the correct answer.
     */
    public TrueFalseQuestions(final String theQuestionText, final boolean theCorrect) {
        super(theQuestionText, String.valueOf(theCorrect));
        myCorrectAnswers = QuestionAnswer.getAnswers();
        authenticateAnswer(myCorrectAnswers, theCorrect);
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
     * @param theCorrectAnswers A string of correct answers.
     * @param theAnswer The user's answer as a boolean (true/false).
     */
    public void authenticateAnswer(final List<String> theCorrectAnswers, final boolean theAnswer) {
        String answerAsString = String.valueOf(theAnswer);
        System.out.println("Correct Answers: " + theCorrectAnswers);
        System.out.println("Answer as String: " + answerAsString);

        if (!theCorrectAnswers.contains(answerAsString)) {
            // Log a message indicating an invalid answer
            LOGGER.warning("Invalid answer: " + theAnswer);
        } else {
            LOGGER.info("Success: " + theAnswer);
        }
    }

    public static void main(String[] args) {
        TrueFalseQuestions trueFalseQuestions = new TrueFalseQuestions("Is the sky blue?", true);

        // Test a correct answer (assuming 0 represents false in the database)
        trueFalseQuestions.authenticateAnswer(QuestionAnswer.getAnswers(), false);

        // Test an incorrect answer (assuming 1 represents true in the database)
        trueFalseQuestions.authenticateAnswer(QuestionAnswer.getAnswers(), true);

    }
}