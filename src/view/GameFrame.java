package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

/**
 * Frame class for Trivia Maze.
 * @author rick_adams.
 * @author Viktoria Dolojan.
 */
public class
GameFrame extends JFrame implements PropertyChangeListener {
    private JFrame myGameFrame;
    private List myGameStats;

    private static final Dimension DIMENSION = new Dimension(500, 500);

    
    public GameFrame() {
        super();
        // Panel is for testing purposes only.
        JPanel panel = new JPanel();
        Dimension panelDim = new Dimension(100, 100);
        panel.setPreferredSize(panelDim);
        add(panel);
        panel.setBackground(Color.PINK);
        frameHelper();

        setFocusable(true);
        requestFocus();
        setVisible(true);
    }

    private void frameHelper() {
        setTitle("Trivia Maze");
        setJMenuBar(menuBarHelper());
        setSize(DIMENSION);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JMenuBar menuBarHelper() {
        final JMenuBar menuBar = new JMenuBar();

        menuBar.add(fileMenu());
        menuBar.add(infoMenu());


        return menuBar;
    }

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
     * This method gets called when a bound property is changed.
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {

    }
}