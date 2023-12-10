package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that handles the bonus clue content.
 * @author Rick Adams
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class ClueManager {
//==================Constants========================//
    /** .txt file that contains the bonus clues. */
    private static final String FILE_PATH = "ClueFile.txt";
    /** Logger constant. */
    private static final Logger LOGGER = Logger.getLogger(ClueManager.class.getName());
//===================Fields=======================//
    /** String to hold clues. */
    private transient final String myClues;
    /**
     * Default constructor.
     * Calls loadFromFile to read in clues from a .txt.
     */
    public ClueManager() {
        this.myClues = loadFromFile();
    }
    /**
     * Reads a prepared .txt file.
     * Contains clues related to Trivia questions.
     * @return Returns either the Clues in String, or "".
     */
    private String loadFromFile() {
        try {
            final List <String> clueLines = Files.readAllLines(Paths.get(FILE_PATH));
            return String.join("\n", clueLines);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING,
                    "Something went wrong with ClueFile I/O", e);
            return "";
        }
    }
    /**
     * Return the clue.
     * @return clue for trivia question
     */
    public String getClues() {
        return this.myClues;
    }
}