import java.awt.Frame;
import javax.swing.SwingUtilities;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Frame().setVisible(true));
    }
}