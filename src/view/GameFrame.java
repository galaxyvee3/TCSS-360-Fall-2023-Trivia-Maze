package view;

import controller.Maze;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Frame class for the GUI representing the Trivia Maze.
 * @author Rick Adams
 * @author Viktoria Dolojan
 * @version Fall 2023
 */
public class GameFrame extends JFrame implements PropertyChangeListener {
    /** The current Trivia Maze being played. */
    private static Maze myMaze;

    private static QuestionPanel qPanel;
    private final PropertyChangeSupport myChangeSupport;

    private static JFrame myGameFrame;

    /** Boolean for whether the game is over. */
    private static boolean myGameOver = true;

    /** Boolean for whether player has escaped the maze. */
    private static final boolean myEscape = false;

    private static final Dimension DIMENSION = new Dimension(500, 500);

    /**
     * Default constructor
     */
    public GameFrame() {
        super();
        myMaze = new Maze(); // create new maze game
        myChangeSupport = new PropertyChangeSupport(this);
        addKeyListener(new MovePlayer()); // add key listener to allow player to move
        frameHelper(); // add info to frame
        showDifficultyMenu(); //
        setFocusable(true);
        requestFocus();
        setVisible(true); // make frame visible
    }

//    public GameFrame(PropertyChangeSupport myChangeSupport) {
//        super();
//        this.myChangeSupport = myChangeSupport;
//    }

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

    /**
     * Enum for game difficulty levels.
     */
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
                throw new IllegalStateException("Not a valid option.");
        }
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
        final JPanel gamePanel = new JPanel(new BorderLayout()); // panel for all info in game
        final GameFrame mazeFrame = new GameFrame(); // frame for game
        final MazePanel mazePanel = new MazePanel(myMaze); // panel for maze
        final QuestionAnswer questionAnswer = new QuestionAnswer();
        qPanel = new QuestionPanel(); // panel for trivia questions

        gamePanel.add(mazePanel, BorderLayout.CENTER); // add maze panel to game panel
        gamePanel.add(qPanel, BorderLayout.SOUTH); // add question panel to game panel
        mazeFrame.add(gamePanel); // add game panel to frame

        myMaze.addPropertyChangeListener(qPanel);
        Map <String, String> randomQuestion = questionAnswer.getRandomQuestion();

        // Set the question on the QuestionPanel
        if (!randomQuestion.isEmpty()) {
            String questionText = randomQuestion.get("QUESTION");
            qPanel.setQuestion(questionText);
        }

        myMaze.addPropertyChangeListener(mazeFrame); // addPropertyChangeListener for all GUI
        myMaze.newGame(); // reset game stats
    }

    public JPanel getQuestionPanel() {
        return qPanel;
    }
    public void saveAndLoad() {

    }

    /**
     * Show game over GUI when player has completed the game, either in success or failure.
     * @param theEvent A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        switch (theEvent.getPropertyName()) {
            case Maze.PROPERTY_GAME_OVER:
                myGameOver = (boolean) theEvent.getNewValue();
                gameOver();
                break;
            case Maze.PROPERTY_UPDATE_MAZE:
                // Update maze-related UI components
                break;
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
    private final MovePlayer myMove = new MovePlayer();

    public void inputHandler(final KeyEvent theEvent) {
        myMove.keyPressed(theEvent);
    }
}