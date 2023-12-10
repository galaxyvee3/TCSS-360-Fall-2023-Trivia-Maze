package controller;

import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import model.ClueManager;
import model.Direction;
import model.Door;
import model.Room;
import view.GameFrame;
import view.QuestionAnswer;

/**
 *Utility class that helps launch game.
 * @author rick_adams.
 * @version Fall 2023.
 * Team 2 - Trivia Maze.
 */
public final class GameLauncher {
    //=============Constant===============//
    /** Logger constant. */
    private static final Logger LOGGER = Logger.getLogger(ClueManager.class.getName());
    /** Constant for timer delay. */
    private static final int DELAY = 1000;

    /** Private contractor. */
    private GameLauncher() {
    }
    /** Launch method. */
    public static void launcher() {
        final QuestionAnswer questionAnswer = new QuestionAnswer();
        final Room room = new Room();
        final Door door = new Door(room, room,
                                   Direction.NORTH, Direction.SOUTH);

        // Create and start the game engine
        final GameEngine gameEngine = new GameEngine(questionAnswer, room, door);

        // Create and show the GUI
        SwingUtilities.invokeLater(() -> {
            GameFrame.createGUI();
            gameEngine.startGameLoop();
        });

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            LOGGER.severe("Thread sleep interrupted");
        }
        // Stop the game engine after the desired duration
        gameEngine.setRunningGame(false);
    }
}
