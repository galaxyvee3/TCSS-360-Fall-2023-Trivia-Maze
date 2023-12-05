package view;

/**
 * Abstract class extended to question classes.
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public abstract class Question {
    /** The trivia question. */
    private final String myQuestionText;

    /** The answer to the trivia question. */
    private final String myAnswerText;

    private Type myType;

    /**
     * Public constructor for object instantiation.
     * @param theQuestionText Question text in string.
     * @param theAnswerText Answer text in string.
     */
    public Question(final String theQuestionText, final String theAnswerText) {
        this.myQuestionText = theQuestionText;
        this.myAnswerText = theAnswerText;
    }

    /**
     * Accessor method that returns the question type.
     * @return the question type.
     */
    public abstract String getQuestionType();

    /**
     * Return the question.
     * @return trivia question
     */
    public String getQuestionText() {
        return myQuestionText;
    }

    /**
     * Return the answer.
     * @return answer to trivia question
     */
    public String getAnswer() {
        return myAnswerText;
    }
    public enum Type {
        MULTIPLE_CHOICE,
        TRUE_FALSE,
        SHORT_ANSWER
    }

    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Generated toString, mostly for debugging/testing.
     * @return Returns the raw Question class via String.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Question{");
        sb.append("myQuestionText='").append(myQuestionText).append('\'');
        sb.append(", myAnswerText='").append(myAnswerText).append('\'');
        sb.append(", myType=").append(myType);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
