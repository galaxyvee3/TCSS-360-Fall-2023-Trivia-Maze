import controller.GameLauncher;
import java.sql.SQLException;
import javax.swing.SwingUtilities;

/**
 * Main driver for Trivia Maze.
 * @author Rick Adams.
 * @author Viktoria Dolojan
 * @version Fall 2023.
 * Trivia Maze - Team 2.
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
    public static void main(final String[] theArgs) throws SQLException {
//        QuestionDatabase.initializeDatabase();
        SwingUtilities.invokeLater(GameLauncher::launcher);
    }
}