package controller;

import javax.swing.SwingUtilities;
import model.Door;
import model.Maze;
import model.Room;
import view.GameFrame;
import view.MazePanel;
import view.QuestionAnswer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class that operates a Trivia Maze game.
 * @author Rick Adams.
 * @author Viktoria Dolojan
 * @version Fall 2023.
 * Trivia Maze - Team 2.
 */
public class GameEngine {
    //=====================Constants==========================//
    /**
     * Constant int for loop delay.
     */
    private static final int TIMER_DELAY = 100;

    //=====================Fields==========================//
    /**
     * QuestionAnswer class object.
     */
    private final QuestionAnswer myQA;
    /**
     * GameFrame class object.
     */
    private final GameFrame myGFrame;
    /**
     * Room class object.
     */
    private final Room myRoom;
    /**
     * Maze class object.
     */
    private final Maze myMaze;
    /**
     * Door class object.
     */
    private final Door myDoor;
    /**
     * Game status boolean.
     */
    private boolean myRunningGame;
    /**
     * MazePanel class object.
     */
    private MazePanel myMazePanel;

    /**
     * Timer object.
     */
    private final Timer myTimer;

    /**
     * Public constructor.
     *
     * @param theQA   QuestionAnswer object.
     * @param theRoom Room object.
     * @param theDoor Door object.
     */
    public GameEngine(final QuestionAnswer theQA,
                      final Room theRoom,
                      final Door theDoor) {
        this.myQA = theQA;
        this.myRoom = theRoom;
        this.myDoor = theDoor;
        this.myMaze = new Maze();
        this.myGFrame = new GameFrame();
        this.myMazePanel = new MazePanel(myMaze);
        this.myRunningGame = true;
        this.myTimer = new Timer();
        myTimer.scheduleAtFixedRate(new GameLoop(), 0, TIMER_DELAY);
        startGameLoop();
    }

    /**
     * Mutator method to start the game timer.
     */
    protected final void startGameLoop() {
        myTimer.scheduleAtFixedRate(new GameLoop(), 0, TIMER_DELAY);
    }

    /**
     * Mutator that sets game status.
     *
     * @param theRunningGame returns the status boolean.
     */
    public void setRunningGame(final boolean theRunningGame) {
        this.myRunningGame = theRunningGame;
    }

    /**
     * Inner game loop class.
     *
     * @author rick_adams.
     * Team 2 - Trivia Maze.
     * @version 2023.
     */
    public class GameLoop extends TimerTask {
        /**
         * Package - protected Constructor.
         */
        protected GameLoop() {
            super();
        }

        /**
         * Method that starts the game.
         */
        @Override
        public void run() {
            if (myRunningGame) {
                // Execute the UI update on the EDT
                SwingUtilities.invokeLater(() -> {
                    myGFrame.getMazePanel().repaint();
                    myGFrame.render();
                });
            } else {
                myTimer.cancel();
            }
        }
    }
}
