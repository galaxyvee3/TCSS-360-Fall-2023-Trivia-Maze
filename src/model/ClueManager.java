package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that handles the bonus clue content.
 * @author rick_adams.
 */
public class ClueManager {
    private final String myClues;
    /**
     * .txt file that contains the bonus clues.
     */
    private static final String CLUE_FILE_PATH = "ClueFile.txt";

    private static final Logger LOGGER = Logger.getLogger(ClueManager.class.getName());

    /**
     * Public constructor.
     * Calls loadFromFile to read in cles from a .txt.
     */
    public ClueManager() {
        this.myClues = loadFromFile();
    }

    public ClueManager(String theClues) {
        this.myClues = theClues;
    }



    private String loadFromFile() {
        try {
            final List<String> clueLines = Files.readAllLines(Paths.get(CLUE_FILE_PATH));
            return String.join("\n", clueLines);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING,
                       "Something went wrong with ClueFile I/O", e);
            return null;
        }
    }
    public String getClues() {
        return this.myClues;
    }
}
