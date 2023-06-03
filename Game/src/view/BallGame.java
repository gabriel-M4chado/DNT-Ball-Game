package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class BallGame extends JPanel implements KeyListener {

    private int lineX;

    public BallGame() {
        addKeyListener(this);

        setFocusable(true); // Dando foco ao Jpanel para receber eventos do KeyListener
        requestFocus();

        lineX = (getWidth() - 200) / 2;
    }

    protected void paintComponent(Graphics ball) {
        //desenha bola
        super.paintComponent(ball);
        ball.setColor(Color.RED);
        ball.fillOval(150, 150, 50, 50);

        // desenha Barra do jogo
        ball.setColor(Color.BLACK);
        int lineWidth = 200;
        int lineY = getHeight() - 20;
        ball.fillRect(lineX, lineY, lineWidth, 10);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // move a linha para esquerda 10px
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (lineX > 0) {
                lineX -= 10;
            }
        }
        // move a linha para direita 10px
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) { 
            int lineWidth = 200; 
            if (lineX + lineWidth < getWidth()) {
                lineX += 10;
            }
        }

        repaint(); // acessa novamente protected void paintComponent 
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
