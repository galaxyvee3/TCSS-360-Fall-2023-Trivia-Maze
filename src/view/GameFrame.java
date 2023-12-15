package view;

import controller.GameLauncher;
import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

/**
 * Frame class for the GUI representing the Trivia Maze.
 * @author Rick Adams
 * @author Viktoria Dolojan
 * @author Justin Ho
 * @version Fall 2023
 */
public class GameFrame extends JFrame implements PropertyChangeListener {
    /** The current Trivia Maze being played. */
    private static Maze myMaze = new Maze();

    private static QuestionPanel myQPanel;

    private final MazePanel myMazePanel;

    /** Boolean for whether the game is over. */
    private static boolean myGameOver;

    private static boolean myEscape;

    /**
     * Default constructor.
     */
    public GameFrame() {
        super();
        setLayout(new BorderLayout());
        this.myMazePanel = new MazePanel(myMaze);
        myGameOver = false;
        myEscape = false;
        addKeyListener(new MovePlayer()); // add key listener to allow player to move
        frameHelper(); // add info to frame
        setFocusable(true);
        requestFocus();
        setVisible(true); // make frame visible
    }

    /**
     * Helps add details to the game frame.
     */
    private void frameHelper() {
        setTitle("Trivia Maze");
        setJMenuBar(menuBarHelper());
        setSize(new Dimension(600, 600));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Create the menu bar for the Trivia Maze frame.
     * @return JMenuBar
     */
    private JMenuBar menuBarHelper() {
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu());
        menuBar.add(infoMenu());
        menuBar.setVisible(true);
        return menuBar;
    }

    /**
     * GUI for game file menu.
     * @return JMenu menu for game file
     */
    private static JMenu fileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem exit = new JMenuItem("Exit");
        final JMenuItem restart = new JMenuItem("Restart");
        final JMenuItem quit = new JMenuItem("Quit");
        final JMenuItem save = new JMenuItem("Save");
        final JMenuItem load = new JMenuItem("Load");

        fileMenu.add(restart);
        fileMenu.add(quit);
        fileMenu.add(save);
        fileMenu.add(load);
        exit.addActionListener(
                e -> System.exit(0));
        restart.addActionListener(e -> GameLauncher.launcher());
        quit.addActionListener(e -> gameOver());
        save.addActionListener(
                e -> {
                    String filename = "";

                    final String[] chooseSave = {"Game 1", "Game 2", "Game 3"};

                    final int choice = JOptionPane.showOptionDialog(null,
                            "Choose Option to save",
                            "Save Game", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                            null, chooseSave, null);

                    if (choice == 0) {
                        filename = "saveGame1.ser";
                    } else if (choice == 1) {
                        filename = "saveGame2.ser";
                    } else if (choice == 2) {
                        filename = "saveGame3.ser";
                    }

                    if (myMaze != null) {
                        try {
                            myMaze.saveGame(filename);
                            final int option = JOptionPane.showConfirmDialog(null,
                                                                       "SUCCESS! GAME SAVED.",
                                                                       "GAME SAVED",
                                                                       JOptionPane.DEFAULT_OPTION);
                            if (option == JOptionPane.OK_OPTION) {
                                System.exit(0);
                            }

                            // Method for serialization of object
                        } catch (final IllegalArgumentException err) {
                            JOptionPane.showMessageDialog(null,
                                    "Pick option to Save this game");
                        }
                    }
                });
            load.addActionListener(e -> {
                String filename = "";

                final String[] chooseSave = {"Game 1", "Game 2", "Game 3"};

                final int choice = JOptionPane.showOptionDialog(null,
                    "Choose the game to load",
                    "Load Game", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, chooseSave, null);

                        if (choice == 0) {
                            filename = "saveGame1.ser";
                        } else if (choice == 1) {
                            filename = "saveGame2.ser";
                        } else if (choice == 2) {
                            filename = "saveGame3.ser";
                        }
                        try {
                            final File file = new File(filename);
                            if (file.exists()) {
                                myMaze.loadGame(filename);
                            }
                            else {
                                JOptionPane.showMessageDialog(null,
                                        "The selected file does not contain a previously saved game state.");
                            }
                    } catch (final IllegalArgumentException | IOException | ClassNotFoundException err) {
                            JOptionPane.showMessageDialog(null,
                                    "The selected file does not contain a previously saved game state.");
                        }
            }

        );
        fileMenu.add(exit);
        return fileMenu;
    }

    /**
     * GUI for game information menu.
     * @return JMenu menu for game info
     */
    private static JMenu infoMenu() {
        final JMenu infoMenu = new JMenu("Info");
        final JMenuItem rules = new JMenuItem("Rules");
        final JMenuItem help = new JMenuItem("Help");
        final JMenuItem about = new JMenuItem("About Game");

        rules.addActionListener(e -> JOptionPane.showMessageDialog(null,
                """
                        In order to advance to the next room, you must answer the given Trivia question.
                        If you answer correctly,  the way forward may be reveled;
                        If you answer incorrectly, the way forward may be forever shut.
                        Answer wisely.\s"""));
        infoMenu.add(rules);

        help.addActionListener(e -> JOptionPane.showMessageDialog(null,
                """
                        There is no help for you, only pain and algorithms class.
                        (This message is under further construction, and does not reflect thoughts,
                        feelings and opinions of Team 2.
                        Math is fun.)\s"""));
        infoMenu.add(help);

        about.addActionListener(e -> JOptionPane.showMessageDialog(null,
                """
                        "Trivia Maze" is a collaborative project developed by Team 2.
                        Any similarities between this and any other projects, past, present, and/or future
                        "Trivia Maze" projects is strictly coincidental.\s"""));
        infoMenu.add(about);

        return infoMenu;
    }

    /**
     * Game over menu for whether player cannot move anymore or has escaped the maze.
     */
    public static void gameOver() {
        final JFrame endFrame = new JFrame("GAME OVER");
        final JPanel endPanel = new JPanel();
        JLabel endLabel;
        if (myEscape) { // label for when player successfully escaped
            endLabel = new JLabel("You escaped the maze!");
        } else { // label for when player is trapped
            endLabel = new JLabel("You could not escape the maze. Try again.");
        }
        final JPanel buttonPanel = new JPanel(new GridLayout(1, 2)); // panel for buttons
        final JButton gameButton = new JButton("Play Again");
        final JButton quitButton = new JButton("Quit");
        buttonPanel.add(gameButton);
        buttonPanel.add(quitButton);
        endPanel.add(endLabel);
        endPanel.add(buttonPanel);
        endFrame.add(endPanel);
        gameButton.addActionListener(e -> GameLauncher.launcher());
        quitButton.addActionListener(e -> System.exit(0));
        endFrame.setSize(300, 100);
        endFrame.setLocationRelativeTo(null); // make frame in center of screen
        endFrame.setVisible(true); // show frame
    }

    /**
     * Create the GUI for the Trivia Maze.
     */
    public static void createGUI() {
        myMaze = new Maze(); // create new maze game
        final GameFrame gameFrame = new GameFrame(); // create frame for game
        // create panels
        final InfoPanel infoPanel = new InfoPanel();
        final MazePanel mazePanel = new MazePanel(myMaze);
        final QuestionPanel questionPanel = new QuestionPanel();

        // add panels to frame
        gameFrame.add(infoPanel, BorderLayout.EAST);
        gameFrame.add(mazePanel, BorderLayout.CENTER);
        gameFrame.add(questionPanel, BorderLayout.SOUTH);

        // add PCL to frame and panels
        myMaze.addPropertyChangeListener(mazePanel);
        myMaze.addPropertyChangeListener(questionPanel);
        myMaze.addPropertyChangeListener(gameFrame);
        gameFrame.setVisible(true);
    }

    public void render() {
        // Update the maze display
        myMazePanel.repaint();
        // Ensure myQPanel is not null before invoking methods on it
        if (myQPanel != null) {
            myQPanel.repaint();
        }
    }

    public void setQuestionPanel(final QuestionPanel theQuestionPanel) {
        myQPanel = theQuestionPanel;
    }

    public MazePanel getMazePanel() {
        return myMazePanel;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals(Maze.PROPERTY_GAME_OVER_SUCCESS)) {
            myEscape = true;
            gameOver();
        } else if (theEvent.getPropertyName().equals(Maze.PROPERTY_GAME_OVER_FAIL)) {
            myEscape = false;
            gameOver();
        }
    }

    /**
     * Private class that allows the player to traverse the maze using the keyboard.
     * @author Viktoria Dolojan
     * @version Fall 2023.
     */
    private static class MovePlayer extends KeyAdapter {

        @Override
        public void keyPressed(final KeyEvent theEvent) {
            // WASD and arrow keys
            if (theEvent.getKeyCode() == KeyEvent.VK_W || theEvent.getKeyCode() == KeyEvent.VK_UP) {
                myMaze.moveUp();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_S || theEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                myMaze.moveDown();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_A || theEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                myMaze.moveLeft();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_D || theEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                myMaze.moveRight();
            }
        }
    }
}