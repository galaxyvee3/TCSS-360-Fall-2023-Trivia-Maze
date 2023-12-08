package view;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

/**
 * QuestionAnswer class for Trivia Maze.
 * @author Viktoria Dolojan
 * @author Rick Adams
 * @version Fall 2023
 */
public class QuestionAnswer {
//======================Constants======================//
    /** Logger constant. */
    private static final Logger LOGGER = Logger.getLogger(QuestionAnswer.class.getName());

    /** URL constant. */
    private static final String URL = "jdbc:sqlite:QuestionsDB.db";

//======================Fields======================//
    /** ArrayList of Trivia Questions for the maze. */
    private static ArrayList<Question> myQuestions;

    /**
     * Public constructor.
     */
    public QuestionAnswer() {
        myQuestions = new ArrayList<Question>();
        fetchQuestions();
        Collections.shuffle(myQuestions);
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
            int index = 1;
            while (rs1.next()) {
                String question = rs1.getString( "QUESTION" );
                String answer = rs1.getString( "ANSWER" );
                String choiceA = rs1.getString("choiceA");
                String choiceB = rs1.getString("choiceB");
                String choiceC = rs1.getString("choiceC");
                Question mc = new MultipleChoiceQuestions(question, answer, choiceA, choiceB, choiceC);
                myQuestions.add(mc);
                System.out.println(index + " " + mc);
                index++;
            }
            ResultSet rs2 = stmt.executeQuery(query2);
            while (rs2.next()) {
                String question = rs2.getString( "QUESTION" );
                boolean answer = Boolean.parseBoolean(rs2.getString( "ANSWER" ));
                Question tf = new TrueFalseQuestions(question, answer);
                myQuestions.add(tf);
                System.out.println(index + " " + tf);
                index++;
            }
            ResultSet rs3 = stmt.executeQuery(query3);
            while (rs3.next()) {
                String question = rs3.getString( "QUESTION" );
                String answer = rs3.getString( "ANSWER" );
                Question sa = new ShortAnswerQuestions(question, answer);
                myQuestions.add(sa);
                System.out.println(index + " " + sa);
                index++;
            }
        } catch (final SQLException e) {
            LOGGER.severe("Question fetch from DB has failed.");
        }
    }

    /**
     * Get the list of trivia questions.
     * @return ArrayList of Questions
     */
    public ArrayList<Question> getQuestions() {
        return myQuestions;
    }

    @Override
    public String toString() {
        return "QuestionAnswer { " + "myQuestions  = " + myQuestions + " }";
    }
}