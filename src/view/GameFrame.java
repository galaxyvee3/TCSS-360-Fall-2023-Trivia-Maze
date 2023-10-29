package view;

import java.awt.Dimension;
import java.awt.List;
import javax.swing.JFrame;

/**
 *
 */
public class GameFrame extends JFrame {
    private JFrame myGameFrame;
    private List myGameStats;

    public GameFrame() {
        super();

        final JFrame frame = new JFrame();

        final Dimension dimension = new Dimension(100, 100);
        frame.setSize(dimension);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    public void menu() {

    }

    public void saveAndLoad() {

    }

    public void gameDifficulty() {

    }
}