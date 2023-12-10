package view;

import javax.swing.*;
import java.awt.*;

/**
 * Class creates the visual representation of the information panel which explains what each symbol represents.
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class InfoPanel extends JPanel {
    public InfoPanel() {
        super();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, 200));
        add(new JLabel("Information"));
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw words
        String str1 = "Current Location of Player";
        String str2 = "Locked Door";
        String str3 = "Closed Door";
        String str4 = "Maze Room";
        String str5 = "Exit Room";
        g2d.setFont(new Font("Arial", Font.ITALIC, 12));
        g2d.drawString(str1, 15, 40);
        g2d.drawString(str2, 15, 60);
        g2d.drawString(str3, 15, 80);
        g2d.drawString(str4, 15, 100);
        g2d.drawString(str5, 15, 120);
        String movement = "Key Binds";
        String w = "Up: W key or Up arrow key";
        String s = "Down: S key or Down arrow key";
        String a = "Left: A key or Left arrow key";
        String d = "Right: D key or Right arrow key";
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString(movement, 0, 160);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString(w, 0, 180);
        g2d.drawString(s, 0, 200);
        g2d.drawString(a, 0, 220);
        g2d.drawString(d, 0, 240);
        String exp1 = "How to win:";
        String exp2 = "Escape the maze by answering trivia";
        String exp3 = "questions correctly and reaching the";
        String exp4 = "exit, otherwise be trapped in the";
        String exp5 = "maze, forever...";
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString(exp1, 0, 280);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString(exp2, 0, 300);
        g2d.drawString(exp3, 0, 320);
        g2d.drawString(exp4, 0, 340);
        g2d.drawString(exp5, 0, 360);

        g2d.setPaint(Color.BLUE); // player location
        g2d.fillOval(0, 30, 10, 10);
        g2d.setPaint(new Color(100,75,50)); // locked door
        g2d.fillRect(0, 50, 10,10);
        g2d.setPaint(Color.BLACK); // closed door
        g2d.fillRect(0, 70, 10, 10);
        g2d.setPaint(Color.DARK_GRAY); // maze room
        g2d.fillRect(0, 90, 10, 10);
        g2d.setPaint(Color.LIGHT_GRAY); // exit room
        g2d.fillRect(0, 110, 10, 10);
    }
}