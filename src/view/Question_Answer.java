package view;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map;

/**
 * Question_Answer class for Trivia Maze.
 * @author Viktoria Dolojan
 */
public class Question_Answer {

    private List<Map<String, String>> myQuestions;
    public Question_Answer() {
        myQuestions = new ArrayList<>();
    }

    public List getQuestions() {
        return null;
    }

    public boolean userAnswer(char theChoice) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }


        public static class TriviaQuestion {
            private String question;
            private String answer;

            public TriviaQuestion(String question, String answer) {
                this.question = question;
                this.answer = answer;
            }

            public String getQuestion() {
                return question;
            }

            public String getAnswer() {
                return answer;
            }

            @Override
            public String toString() {
                return "TriviaQuestion{" +
                        "question='" + question + '\'' +
                        ", answer='" + answer + '\'' +
                        '}';
            }
        }
    }