package model;

import controller.Maze;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Map;
import view.Question;
import view.QuestionAnswer;
import view.QuestionPanel;


/**
 * Class that operates a Trivia Maze game.
 * @author Rick Adams
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class GameEngine implements PropertyChangeListener {

    private PropertyChangeSupport myPCS;
    private final QuestionAnswer myQA;
    private final QuestionPanel myQuestionPanel;
    private Question myQuestion;
    private final Room myRoom;

    private Maze myMaze;

    private final Door myDoor;

    /**
     * Default constructor.
     */
    public GameEngine(final QuestionAnswer theQA,
                      final QuestionPanel theQP,
                      final Question theQuestion,
                      final Room theRoom,
                      final Door theDoor) {
        super();
        this.myQA = theQA;
        this.myQuestionPanel = theQP;
        this.myQuestion = theQuestion;
        this.myRoom = theRoom;
        this.myDoor = theDoor;
        myMaze = new Maze();
        myQuestionPanel.addPropertyChangeListener(this);

        myQuestionPanel.addPropertyChangeListener(myRoom);
        myQuestionPanel.addPropertyChangeListener(myDoor);

        showNextQuestion();
    }

    private void initialize() {
        // Add any initialization logic here
    }

    public void runGame() {
        // Add logic to run the game
    }
    public void setMyQuestion(Question myQuestion) {
        Question oldQuestion = this.myQuestion;
        this.myQuestion = myQuestion;
        myPCS.firePropertyChange("myQuestion", oldQuestion, myQuestion);
    }

    private void showNextQuestion() {
        List<Map<String, String>> questions = myQA.getQuestions();
        if (!questions.isEmpty()) {
            Map<String, String> questionData = questions.remove(0);
            myQuestion = myQA.createQuestion(questionData.get("theType"),
                                             questionData.get("param1"),
                                             questionData.get("param2"));
            myQuestionPanel.displayQuestion(myQuestion);

            // Notify QuestionPanel about the new question
            myQuestionPanel.firePropertyChange("myQuestion", null, myQuestion);

        } else {
            // No more questions, game over logic
        }
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("myQuestion".equals(evt.getPropertyName())) {
            final String newQuestion = (String) evt.getNewValue();
        }
    }
}
