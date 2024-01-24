package model;

import java.io.Serializable;

/**
 * Class extended to question classes.
 *
 * @author Rick Adams.
 * @author Viktoria Dolojan.
 * @version Fall 2023.
 * Trivia Maze - Team 2.
 */
public class Question implements Serializable {
    /** The trivia question. */
    private final String myQuestion;

    /** The answer to the trivia question. */
    private final String myAnswer;

    /** The type of trivia question. */
    private Type myType;

    /**
     * Public constructor for object instantiation.
     *
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
     *
     * @return trivia question
     */
    public String getQuestion() {
        return myQuestion;
    }

    /**
     * Get the answer to the trivia question.
     *
     * @return String answer to trivia question
     */
    public String getAnswer() {
        return myAnswer;
    }

    /**
     * Get the type of trivia question.
     *
     * @return String question type
     */
    public String getQuestionType() {
        return myType.toString();
    }

    /**
     * Set the type of trivia question.
     *
     * @param myType Type of trivia question.
     */
    protected void setMyType(final Type myType) {
        this.myType = myType;
    }

    /**
     * Accessor for the question type.
     *
     * @return Returns the type.
     */
    public Type getMyType() {
        return this.myType;
    }
    /**
     * Enum class for types of trivia questions.
     */
    public enum Type {
        MULTIPLE_CHOICE, TRUE_FALSE, SHORT_ANSWER, DEFAULT
    }

    /**
     * Generated toString, mostly for debugging/testing.
     *
     * @return Returns the raw Question class via String.
     */
    @Override
    public String toString() {
        return "Trivia Question"
               + "\n{ Question = " + myQuestion
               + "\nAnswer = " + myAnswer
               + "\nType = " + myType + " }\n";
    }
}
