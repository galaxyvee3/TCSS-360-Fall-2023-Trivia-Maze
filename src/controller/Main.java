
package controller;

import view.GameFrame;
import view.QuestionDatabase;

import javax.swing.SwingUtilities;
import view.GameFrame;
import view.QuestionDatabase;

/**
<<<<<<<< HEAD:src/model/Main.java
 * controller.Main driver for Trivia Maze.
========
 * Main driver for Trivia Maze.
>>>>>>>> bf6f804a851c073b50f7ffc899660d3759d827b0:src/Main.java
 * @author Rick Adams
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public final class Main {
    /**
     * Private default constructor.
     */
    private Main() {
    }

    /**
     * Main method.
     * @param theArgs command line args
     */
    public static void main(final String[] theArgs) {
        QuestionDatabase.initializeDatabase();
        SwingUtilities.invokeLater(GameFrame::createGUI);
    }
}
