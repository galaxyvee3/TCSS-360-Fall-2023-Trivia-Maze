package model;

import controller.Maze;

/**
 * Class that operates a Trivia Maze game.
 * @author rick_adams
 * @author Viktoria Dolojan
 * @version Fall 2023.
 */
public class GameEngine {
    /* The current Trivia Maze being played. */
    private Maze myMaze = null;

    /* Boolean for whether there is a running Trivia Maze game. */
    private boolean myGameStatus = false;

    /**
     * Default constructor.
     */
    public GameEngine() {
        myMaze = new Maze();
        myGameStatus = true;
    }

    private void initialize() {
        
    }
    public void runGame() {

    }
}