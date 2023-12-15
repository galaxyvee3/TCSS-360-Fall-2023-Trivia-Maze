package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    private static final String URL = QuestionDatabase.dataSourceString();

//======================Fields======================//
    /** ArrayList of Trivia Questions for the maze. */
    private static List <Question> myQuestions;

    /**
     * Public constructor.
     */
    public QuestionAnswer() {
        myQuestions = new ArrayList<>();
        fetchQuestions();
        Collections.shuffle(myQuestions);
    }

    /**
     * Create a list of trivia questions from the database.
     */
    private static void fetchQuestions() {
        final String query1 = "SELECT * FROM MultipleChoiceQuestions";
        final String query2 = "SELECT * FROM TrueFalseQuestions";
        final String query3 = "SELECT * FROM ShortAnswerQuestions";
        try (Connection conn = DriverManager.getConnection(URL)) {
            final Statement stmt = conn.createStatement();
            final ResultSet rs1 = stmt.executeQuery(query1);
            while (rs1.next()) {
                final String question = rs1.getString("QUESTION");
                final String answer = rs1.getString("ANSWER");
                final String choiceA = rs1.getString("choiceA");
                final String choiceB = rs1.getString("choiceB");
                final String choiceC = rs1.getString("choiceC");
                final Question mc = new MultipleChoiceQuestions(question,
                                                                answer,
                                                                choiceA,
                                                                choiceB,
                                                                choiceC);
                myQuestions.add(mc);
            }
            final ResultSet rs2 = stmt.executeQuery(query2);
            while (rs2.next()) {
                final String question = rs2.getString("QUESTION");
                final String answer = rs2.getString("ANSWER");
                final Question tf;
                if (answer.equalsIgnoreCase("1")) {
                    tf = new TrueFalseQuestions(question, "true");
                } else {
                    tf = new TrueFalseQuestions(question, "false");
                }
                myQuestions.add(tf);
            }
            final ResultSet rs3 = stmt.executeQuery(query3);
            while (rs3.next()) {
                final String question = rs3.getString("QUESTION");
                final String answer = rs3.getString("ANSWER");
                final Question sa = new ShortAnswerQuestions(question, answer);
                myQuestions.add(sa);
            }
        } catch (final SQLException e) {
            LOGGER.severe("Question fetch from DB has failed.");
        }
    }

    /**
     * Get the list of trivia questions.
     * @return ArrayList of Questions
     */
    public List<Question> getQuestions() {
        return myQuestions;
    }

    @Override
    public String toString() {
        return "QuestionAnswer { " + "myQuestions  = " + myQuestions + " }";
    }
}