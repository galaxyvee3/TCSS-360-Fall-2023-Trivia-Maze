package view;

import javax.swing.*;

/**
 * view.Main driver for Trivia Maze.
 * @author rick_adams
 * @author Viktoria Dolojan
 */
public class Main {
    /**Private constructor.*/
    private Main() {
    }
    /**
     * view.Main method.
     * @param theArgs command line args.
     */
    public static void main(final String[] theArgs) {
        QuestionDatabase.initializeDatabase();
        SwingUtilities.invokeLater(GameFrame::createGUI);
    }
}