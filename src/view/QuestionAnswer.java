package view;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

/**
 * QuestionAnswer class for Trivia Maze.
 * @author Viktoria Dolojan
 * @author rick_adams
 * @version Fall 2023
 */
public class QuestionAnswer {
//======================Constants======================//
    /** Logger constant. **/
    private static final Logger LOGGER = Logger.getLogger(QuestionAnswer.class.getName());

    private static final String QUESTION = "QUESTION";

    private static final  String ANSWER = "ANSWER";

    private static final String QUESTION_ID = "QuestionID";

    private static final String URL = "jdbc:sqlite:QuestionsDB.db";
    /** Random constant. */
    private static final Random RANDOM = new Random();
//======================Fields======================//
    private static int myCurrentIndex;


    private static ArrayList<Question> myQuestions = new ArrayList<>();

    /**
     * Public constructor.
     */
    public QuestionAnswer() {
        myCurrentIndex = 0;
        //myQuestions = new ArrayList<Question>();
        fetchQuestions();
    }

    /**
     * Create a list of trivia questions from the database.
     */
    private static void fetchQuestions() {
        String query1 = "SELECT * FROM MultipleChoiceQuestions";
        String query2 = "SELECT * FROM TrueFalseQuestions";
        String query3 = "SELECT * FROM ShortAnswerQuestions";
        try (Connection conn = DriverManager.getConnection(URL)) {
            Statement stmt = conn.createStatement();
            ResultSet rs1 = stmt.executeQuery(query1);
            while (rs1.next()) {
                String question = rs1.getString( "QUESTION" );
                String answer = rs1.getString( "ANSWER" );
                String choiceA = rs1.getString("choiceA");
                String choiceB = rs1.getString("choiceB");
                String choiceC = rs1.getString("choiceC");
                Question mc = new MultipleChoiceQuestions(question, answer, choiceA, choiceB, choiceC);
                myQuestions.add(mc);
                System.out.println(mc);
            }
            ResultSet rs2 = stmt.executeQuery(query2);
            while (rs2.next()) {
                String question = rs2.getString( "QUESTION" );
                boolean answer = Boolean.parseBoolean(rs2.getString( "ANSWER" ));
                Question tf = new TrueFalseQuestions(question, answer);
                myQuestions.add(tf);
                System.out.println(tf);
            }
            ResultSet rs3 = stmt.executeQuery(query3);
            while (rs3.next()) {
                String question = rs3.getString( "QUESTION" );
                String answer = rs3.getString( "ANSWER" );
                Question sa = new ShortAnswerQuestions(question, answer);
                myQuestions.add(sa);
                System.out.println(sa);
            }
        } catch (final SQLException e) {
            LOGGER.severe("Question fetch from DB has failed.");
        }
    }

    /**
     * Generated toString.
     * @return Returns the raw QuestionAnswer class via string.
     */
    @Override
    public String toString() {
        return "QuestionAnswer { " + "myQuestions  = " + myQuestions + " }";
    }

    public static void main(String[] args) {
        QuestionAnswer qa = new QuestionAnswer();
    }
}