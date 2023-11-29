package view;

/**
 * For testing purposes.
 */
public class ViewMain {
    public static void main(String[] args) {
        ShortAnswerQuestions shortA = new ShortAnswerQuestions("What is the capital of Washington",
                                                             "Olympia");
        //correct
        shortA.authenticateAnswer(QuestionAnswer.getAnswers(), "Olympia");
        //Incorrect
        shortA.authenticateAnswer(QuestionAnswer.getAnswers(), "Portland");
    }
}
