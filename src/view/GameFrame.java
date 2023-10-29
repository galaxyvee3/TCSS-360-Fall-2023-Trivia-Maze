package view;

import java.awt.Dimension;
import java.awt.List;
import javax.swing.*;

/**
 *
 */
public class GameFrame extends JFrame {
    private JFrame myGameFrame;
    private List myGameStats;

    private static final Dimension DIMENSION = new Dimension(100, 100);
    
    public GameFrame() {
        super();

        final JFrame frame = new JFrame();
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuFile.add(menuItemExit);
        frameHelper();
        setFocusable(true);
        setVisible(true);
    }

    private void frameHelper() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DIMENSION);
        setResizable(false);
        setTitle("Trivia Maze");
        
    }

    public JMenuBar menuBarHelper(final JMenuBar theBar) {

        return theBar;
    }
    public void menuHelper() {

    }

    public void saveAndLoad() {

    }

    public void gameDifficulty() {

    }
}