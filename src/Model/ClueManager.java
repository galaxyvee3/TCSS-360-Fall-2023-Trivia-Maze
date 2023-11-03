package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClueManager {
    private final String myClues;

    private static final String CLUE_FILE_PATH = "Trizia_Maze_Team2\\CLueText.txt";

    private static final Logger myLogger = Logger.getLogger(ClueManager.class.getName());


    public ClueManager() {
        this.myClues = loadFromFile();
    }
    private String loadFromFile() {
        try {
            final List<String> clueLines = Files.readAllLines(Paths.get(CLUE_FILE_PATH));
            return String.join("\n", clueLines);
        } catch (IOException e) {
            myLogger.log(Level.WARNING, "Something went wrong with ClueFile I/O", e);
            return ""; // Return an empty string when there's an issue
        }
    }


    private String getClues() {
        return this.myClues;
    }
}
