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
public class GameFrame extends JFrame implements PropertyChangeListener {
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


        setSize(DIMENSION);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Trivia Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JMenuBar menuBarHelper() {
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu());
        
        return menuBar;
    }
    public void menuHelper() {

    }

    private static JMenu fileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem exit = new JMenuItem("Exit");

        
        exit.addActionListener(
                e -> System.exit(0));
        fileMenu.add(exit);
        return fileMenu;
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