package view;

/**
 * For testing purposes.
 */
public class ViewMain {
    public static void main(String[] args) {
        // Create an instance of ShortAnswerQuestions with a known question and answer
        TrueFalseQuestions trueFalseQuestions = new TrueFalseQuestions("Does the limit exist?", "false");

        // Test a correct answer
        trueFalseQuestions.authenticateAnswer(QuestionAnswer.getAnswers(), false);

        // Test an incorrect answer
        trueFalseQuestions.authenticateAnswer(QuestionAnswer.getAnswers(), true);

    }
}
