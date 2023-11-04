package tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TrialMain {
    public static void main(String[] args) throws IOException {
        // Debug: Print the current working directory
        String workingDirectory = System.getProperty("user.dir");
        System.out.println("Current working directory: " + workingDirectory);

        // Debug: Print the file path
        String filePath = "ClueTest.txt";
        System.out.println("File path: " + filePath);

        // Debug: Load and print file contents
        String fileContents = loadFile(filePath);
        System.out.println("File contents: ");
        System.out.println(fileContents);
        Path fileName
                = Path.of("ClueTest.txt");

        String str = Files.readString(fileName);

        System.out.println(str);
    }

    private static String loadFile(String filePath) {
        // Load and return file contents
        // This is where you would read the file
        // and return its contents as a String
        return "Sample file contents";
    }
}
