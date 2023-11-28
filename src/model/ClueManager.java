package model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that handles the bonus clue content.
 * @author Rick Adams
 * @author Justin Ho
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class ClueManager implements Serializable{
    @Serial
    private static final long serialVersionUID = 3L;

    /*

    public static void main (String[] args) {
        ClueManager clueManager = new ClueManager(); // Create instance of object Class
        try (FileOutputStream fileOut = new FileOutputStream("clueManager.ser");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(clueManager); // Serialize door object to room.ser

            System.out.println("ClueManager object has been serialized!\n" +
                    "Data before serialization");
        } catch (
                IOException e
        ) {
            e.printStackTrace();

        }
        // Deserialization
        try (FileInputStream fileIn = new FileInputStream("clueManager.ser");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            clueManager = (ClueManager)objectIn.readObject(); // Deserialize the Maze object
            System.out.println("ClueManager object deserialized!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

     */
    private final String myClues;

    /** .txt file that contains the bonus clues. */
    private static final String CLUE_FILE_PATH = "ClueFile.txt";

    private static final Logger LOGGER = Logger.getLogger(ClueManager.class.getName());

    /**
     * Default constructor.
     * Calls loadFromFile to read in cles from a .txt.
     */
    public ClueManager() {
        this.myClues = loadFromFile();
    }

    /**
     *
     * @return
     */
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

    /**
     * Return the clue.
     * @return clue for trivia question
     */
    public String getClues() {
        return this.myClues;
    }
}
