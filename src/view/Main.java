package view;

import javax.swing.SwingUtilities;

/**
 * view.Main driver for Trivia Maze.
 * @author rick_adams.
 * @author Viktoria Dolojan.
 */
public class Main {
    /**Private constructor.*/
    private Main() {
    }
    /**
     * view.Main method.
     * @param thrArgs command line args.
     */
    public static void main(final String[] thrArgs) {
        SwingUtilities.invokeLater(() -> {
           final GameFrame gameFrame = new GameFrame();
        });
    }
}