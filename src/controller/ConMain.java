package controller;

import model.Direction;
import model.Door;
import model.Room;
import view.QuestionAnswer;

import java.sql.SQLException;

public class ConMain {
    public static void main(String[] args) throws SQLException {
        QuestionAnswer myQA = new QuestionAnswer();
        Room myRoom = new Room();
        Door myDoor = new Door(myRoom, myRoom, Direction.NORTH, Direction.SOUTH);

        // Create and start the game engine
        GameEngine gameEngine = new GameEngine(myQA, myRoom, myDoor);

        try {
            Thread.sleep(10000);  // Sleep for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop the game engine after the desired duration
        gameEngine.setRunningGame(false);
    }
}
