package view;

import controller.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Frame class for the GUI representing the Trivia Maze.
 * @author Rick Adams
 * @author Viktoria Dolojan
 * @version Fall 2023
 */
public class GameFrame extends JFrame implements PropertyChangeListener {
    /** The current Trivia Maze being played. */
    private static Maze myMaze = null;

    private PropertyChangeSupport myChangeSupport;

    private static JFrame myGameFrame;

    /** Boolean for whether the game is over. */
    private static boolean myGameOver = true;

    /** Boolean for whether player has escaped the maze. */
    private static boolean myEscape = false;

    private static final Dimension DIMENSION = new Dimension(500, 500);

    /**
     * Default constructor
     */
    public GameFrame() {
        super();
        showDifficultyMenu();
        // Panel is for testing purposes only.
        myMaze = new Maze();
        addKeyListener(new MovePlayer());
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(200, 200));

        DrawMaze maze = new DrawMaze(new Maze());
        panel.add(maze, BorderLayout.CENTER);

        add(panel);
        panel.setBackground(Color.LIGHT_GRAY);
        frameHelper();

        setFocusable(true);
        requestFocus();
        setVisible(true);
    }
    private void startGame() {
        // Instantiate the maze with the selected difficulty
        Maze maze = new Maze();
        // Pass the maze to the DrawMaze panel
        DrawMaze drawMaze = new DrawMaze(maze);

        // Add the DrawMaze panel to the frame
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(500, 500));
        panel.add(drawMaze, BorderLayout.CENTER);
        add(panel);
        panel.setBackground(Color.LIGHT_GRAY);

    }

    private void showDifficultyMenu() {
        Object[] options = {"Easy", "Medium", "Hard"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Select Difficulty",
                "Difficulty Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 0:
                setDifficulty(difficultyLevel.EASY);
                break;
            case 1:
                setDifficulty(difficultyLevel.MEDIUM);
                break;
            case 2:
                setDifficulty(difficultyLevel.HARD);
                break;
            default:
                setDifficulty(difficultyLevel.MEDIUM);
        }
        // Start the game with the selected difficulty
        startGame();
    }

    private enum difficultyLevel {
        EASY, MEDIUM, HARD
    }

    /**
     * Method to set the game difficulty based on the user's selection
     * Adjust game parameters based on difficulty
     **/
    private void setDifficulty (difficultyLevel level) {
        switch(level) {
            case EASY:
                break;
            // Code here
            case MEDIUM:
                break;

            case HARD:
                break;

            default:
                break;
        }
    }

    public GameFrame(PropertyChangeSupport myChangeSupport) {
        super();
        this.myChangeSupport = myChangeSupport;
    }
    /**
     *
     */
    private void frameHelper() {
        setTitle("Trivia Maze");
        setJMenuBar(menuBarHelper());
        setSize(DIMENSION);
        setResizable(false);
        setLocationRelativeTo(null);
        myGameOver = false;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Create the menu bar for the Trivia Maze frame.
     * @return JMenuBar
     */
    public JMenuBar menuBarHelper() {
        final JMenuBar menuBar = new JMenuBar();

        menuBar.add(fileMenu());
        menuBar.add(infoMenu());


        return menuBar;
    }

    /**
     *
     * @return JMenu
     */
    private static JMenu fileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem exit = new JMenuItem("Exit");
        final JMenuItem start = new JMenuItem("Start");
        final JMenuItem quit = new JMenuItem("Quit");

        fileMenu.add(start); //TODO: Install game commencement logic.
        fileMenu.add(quit); //TODO: Install game end logic.

        exit.addActionListener(
                e -> System.exit(0));
        fileMenu.add(exit);
        return fileMenu;
    }

    /**
     *
     * @return JMenu
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
                        "Trivia Maze" projects is strictly coincidental,
                        and in no way an omission that their project is superior;
                        Because this one is better.\s"""));
        infoMenu.add(about);

        return infoMenu;
    }
    public void saveAndLoad() {

    }

    public void gameDifficulty() {

    }

    /**
     * Game over menu for whether player cannot move anymore or has escaped the maze.
     */
    public static void gameOver() {
        final JFrame endFrame = new JFrame("GAME OVER");
        final JPanel endPanel = new JPanel();
        JLabel endLabel = new JLabel();
        if (myEscape) { // label for when player successfully escaped
            endLabel = new JLabel("You escaped the maze!");
        } else { // label for when player is trapped
            endLabel = new JLabel("You could not escape the maze. Try again.");
        }
        endFrame.setSize(100,100);
        endFrame.setVisible(true);
    }

    /**
     * Create the GUI for the Trivia Maze.
     */
    public static void createGUI() {
        // create new frame for game
        final GameFrame mazeFrame = new GameFrame();


        // reset game stats
        myMaze.newGame();
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param theEvent A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {

    }

    /**
     * Private class that allows the player to traverse the maze using the keyboard.
     * @author Viktoria Dolojan
     * @version Fall 2023.
     */
    private class MovePlayer extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            // WASD and arrow keys
            if (theEvent.getKeyCode() == KeyEvent.VK_W || theEvent.getKeyCode() == KeyEvent.VK_UP) {
                System.out.println(myMaze.moveUp());
            } else if (theEvent.getKeyCode() == KeyEvent.VK_S || theEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                System.out.println(myMaze.moveDown());
            } else if (theEvent.getKeyCode() == KeyEvent.VK_A || theEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                System.out.println(myMaze.moveLeft());
            } else if (theEvent.getKeyCode() == KeyEvent.VK_D || theEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                System.out.println(myMaze.moveRight());
            }
        }
    }
}