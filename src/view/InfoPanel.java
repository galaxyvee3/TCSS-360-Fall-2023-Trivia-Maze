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
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(220, 200));
        add(new JLabel("Information"));
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw strings
        String str1 = "Current Location of Player";
        String str2 = "Locked Door";
        String str3 = "Closed Door";
        String str4 = "Current Door Being Attempted";
        g2d.setFont(new Font("Arial", Font.ITALIC, 12));
        g2d.drawString(str1, 25, 40);
        g2d.drawString(str2, 25, 60);
        g2d.drawString(str3, 25, 80);
        g2d.drawString(str4, 25, 100);
        String movement = "Key Binds";
        String w = "Up: W key or Up arrow key";
        String s = "Down: S key or Down arrow key";
        String a = "Left: A key or Left arrow key";
        String d = "Right: D key or Right arrow key";
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString(movement, 10, 140);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString(w, 10, 160);
        g2d.drawString(s, 10, 180);
        g2d.drawString(a, 10, 200);
        g2d.drawString(d, 10, 220);
        String exp1 = "How to win:";
        String exp2 = "Escape the maze by answering trivia";
        String exp3 = "questions correctly and reaching the";
        String exp4 = "exit, otherwise be trapped in the";
        String exp5 = "maze, forever...";
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString(exp1, 10, 260);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString(exp2, 10, 280);
        g2d.drawString(exp3, 10, 300);
        g2d.drawString(exp4, 10, 320);
        g2d.drawString(exp5, 10, 340);

        g2d.setPaint(Color.BLUE); // player location
        g2d.fillOval(10, 30, 10, 10);
        g2d.setPaint(new Color(100,75,50)); // locked door
        g2d.fillRect(10, 50, 10,10);
        g2d.setPaint(Color.BLACK); // closed door
        g2d.fillRect(10, 70, 10, 10);
        g2d.setPaint(Color.WHITE); // current door
        g2d.fillRect(10, 90, 10, 10);
    }
}