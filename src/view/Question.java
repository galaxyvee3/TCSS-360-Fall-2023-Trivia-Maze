package view;

/**
 * Abstract class extended to question classes.
 * @author Rick Adams
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class Question {
    /** The trivia question. */
    private final String myQuestion;

    /** The answer to the trivia question. */
    private final String myAnswer;

    /** The type of trivia question. */
    private Type myType;

    /**
     * Public constructor for object instantiation.
     * @param theQuestion Question text in string.
     * @param theAnswer Answer text in string.
     */
    public Question(final String theQuestion, final String theAnswer) {
        this.myQuestion = theQuestion;
        this.myAnswer = theAnswer;
        myType = Type.DEFAULT;
    }
    /**
     * Get the trivia question.
     * @return trivia question
     */
    public String getQuestion() {
        return myQuestion;
    }

    /**
     * Get the answer to the trivia question.
     * @return String answer to trivia question
     */
    public String getAnswer() {
        return myAnswer;
    }

    /**
     * Get the type of trivia question.
     * @return String question type
     */
    public String getQuestionType() {
        return myType.toString();
    }

    /**
     * Set the type of trivia question.
     * @param myType Type of trivia question
     */
    public void setMyType(Type myType) {
        this.myType = myType;
    }

    /**
     * Enum class for types of trivia questions.
     */
    public enum Type {
        MULTIPLE_CHOICE,
        TRUE_FALSE,
        SHORT_ANSWER,
        DEFAULT
    }

    /**
     * Generated toString, mostly for debugging/testing.
     * @return Returns the raw Question class via String.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trivia Question {");
        sb.append("Question = '").append(myQuestion).append('\'');
        sb.append(", Answer = '").append(myAnswer).append('\'');
        sb.append(", Type = ").append(myType);
        sb.append('}');
        return sb.toString();
    }
}