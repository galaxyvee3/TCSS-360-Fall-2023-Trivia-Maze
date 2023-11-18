package model;

import controller.Maze;
import view.QuestionAnswer;
import view.QuestionPanel;

/**
 * Class that operates a Trivia Maze game.
 * @author Rick Adams
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class GameEngine {
    private final QuestionAnswer myQA;

    private final QuestionPanel myQP;


    /**
     * Default constructor.
     */
    public GameEngine(final QuestionAnswer theQA, final QuestionPanel theQP) {
        super();
        this.myQA = theQA;
        this.myQP = theQP;
    }

    private void initialize() {
        
    }
    public void runGame() {

    }
}