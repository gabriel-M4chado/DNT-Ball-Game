package view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BallGame extends JPanel {
    protected void paintComponent(Graphics ball) {
        super.paintComponent(ball);
        ball.setColor(Color.RED);
        ball.fillOval(150, 150, 50, 50);

        // Barra do jogo 
        ball.setColor(Color.BLACK);
        int lineWidth = 200; 
        int lineY = getHeight() - 20; 
        ball.fillRect(150, lineY, lineWidth, 10);
    }
}
