package controller;

import model.Direction;
import model.Door;
import model.Room;
import view.QuestionAnswer;

public class ConMain {
    public static void main(String[] args) {
        QuestionAnswer myQA = new QuestionAnswer();  // You need to provide an appropriate constructor
        Room myRoom = new Room();  // You need to provide an appropriate constructor
        Door myDoor = new Door(myRoom, myRoom, Direction.NORTH, Direction.SOUTH);  // You need to provide an appropriate constructor

        // Create and start the game engine
        GameEngine gameEngine = new GameEngine(myQA, myRoom, myDoor);

        // You can simulate player interactions or other events as needed

        // For this example, let's run the game for a certain duration (e.g., 10 seconds)
        try {
            Thread.sleep(10000);  // Sleep for 10 seconds (adjust as needed)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop the game engine after the desired duration
        gameEngine.setRunningGame(false);
    }
}
