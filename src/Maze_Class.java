import java.util.Arrays;

/**
 * Maze class for Trivia Maze, Team 2.
 * @author Justin Ho
 *
 *
 */

public class Maze_Class {
    private char[][] myGrid; // 2D array to represent layout of Maze
    private int myEntryRow;
    private int myExitRow;
    private int myEntryCol;
    private int myExitCol;
    private int myPeople;

    public Maze_Class (int rows, int columns)  {
        myEntryRow = 0;
        myEntryCol = 0;
        // Default Exit at Bottom Right Corner
        myExitRow = rows - 1;
        myExitCol = columns - 1;
        myGrid = new char[rows][columns];
        for (char[] row: myGrid) {
            Arrays.fill(row, ' ');
        }






    }




}

