package view;

import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

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
    private static final boolean ESCAPE = false;

    private static final Dimension DIMENSION = new Dimension(600, 600);

    /**
     * Default constructor.
     */
    public GameFrame() {
        super();
        myMaze = new Maze(); // create new maze game
        myChangeSupport = new PropertyChangeSupport(this); // create new pcs
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
     * GUI for game file menu.
     * @return JMenu menu for game file
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
                        "Trivia Maze" projects is strictly coincidental,
                        and in no way an omission that their project is superior;
                        Because this one is better.\s"""));
        infoMenu.add(about);

        return infoMenu;
    }

    /**
     * Game over menu for whether player cannot move anymore or has escaped the maze.
     */
    public static void gameOver() {
        final JFrame endFrame = new JFrame("GAME OVER");
        final JPanel endPanel = new JPanel();
        JLabel endLabel = new JLabel();
        if (myMaze.getGameOver()) { // label for when player successfully escaped
            endLabel = new JLabel("You escaped the maze!");
        } else { // label for when player is trapped
            endLabel = new JLabel("You could not escape the maze. Try again.");
        }
        final JPanel buttonPanel = new JPanel(new GridLayout(1,2)); // panel for buttons
        final JButton gameButton = new JButton("Play Again");
        final JButton quitButton = new JButton("Quit");
        buttonPanel.add(gameButton);
        buttonPanel.add(quitButton);
        endPanel.add(endLabel);
        endPanel.add(buttonPanel);
        endFrame.add(endPanel);
        endFrame.setSize(300,100);
        endFrame.setLocationRelativeTo(null); // make frame in center of screen
        endFrame.setVisible(true); // show frame
    }

    /**
     * Create the GUI for the Trivia Maze.
     */
    public static void createGUI() {
        final JPanel gamePanel = new JPanel(new BorderLayout()); // panel for all info in game
        final GameFrame mazeFrame = new GameFrame(); // frame for game
        final MazePanel mazePanel = new MazePanel(myMaze); // panel for maze
        QuestionAnswer questionAnswer = new QuestionAnswer();
        final QuestionPanel qPanel = new QuestionPanel();

        gamePanel.add(mazePanel, BorderLayout.CENTER); // add maze panel to game panel
        gamePanel.add(qPanel, BorderLayout.SOUTH); // add question panel to game panel
        mazeFrame.add(gamePanel); // add game panel to frame

        Map<String, String>randomQuestion = QuestionAnswer.getRandomQuestion();

        // Set the question on the QuestionPanel
        if (!randomQuestion.isEmpty()) {
            String questionText = randomQuestion.get("QUESTION");
            qPanel.setCurrentQuestion(questionText);
        }
        myMaze.addPropertyChangeListener(mazePanel); // add PCL for maze
        myMaze.addPropertyChangeListener(qPanel); // add PCL for question
        myMaze.addPropertyChangeListener(mazeFrame); // add PCL for frame
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
        String propertyName = theEvent.getPropertyName();
        if (propertyName.equals(Maze.PROPERTY_GAME_OVER)) {
            myGameOver = (boolean) theEvent.getNewValue();
            //boolean escaped = myMaze.getEscapeStatus();
            gameOver();
        } /*else if (propertyName.equals(QuestionPanel.PROPERTY_CURRENT_QUESTION)) {
            String currentQuestion = (String) theEvent.getNewValue();
            askQuestion(currentQuestion);
        }*/
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
