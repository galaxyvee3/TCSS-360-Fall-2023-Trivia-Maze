package model;

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
public class GameEngine {
    private final QuestionAnswer myQA;

    private final QuestionPanel myQuestionPanel;

    private Question myQuestion;

    private  final Room myRoom;


    /**
     * Default constructor.
     */
    public GameEngine(final QuestionAnswer theQA,
                      final QuestionPanel theQP,
                      final Question theQuestion,
                      final Room theRoom) {
        super();
        this.myQA = theQA;
        this.myQuestionPanel = theQP;
        this.myQuestion = theQuestion;
        this.myRoom = theRoom;
    }

    private void initialize() {
        
    }
    public void runGame() {

    }
    private void showNextQuestion() {
        List <Map <String, String>> questions = myQA.getQuestions();
        if (!questions.isEmpty()) {
            Map<String, String> questionData = questions.remove(0);
            myQuestion = myQA.createQuestion(questionData.get("type"), questionData.get("param1"),
                                               questionData.get("param2"));
            myQuestionPanel.displayQuestion(myQuestion);

            // Notify QuestionPanel about the new question
            myQuestionPanel.firePropertyChange();
        } else {
            System.exit(0);
        }
    }

}