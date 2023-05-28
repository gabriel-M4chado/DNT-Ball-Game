package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BallGame extends JPanel{
    protected void paintComponent(Graphics ball) {
        super.paintComponent(ball);
        ball.setColor(Color.RED);
        ball.fillOval(150, 150, 50, 50);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
}
