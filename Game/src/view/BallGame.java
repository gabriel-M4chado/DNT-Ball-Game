package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BallGame extends JPanel implements KeyListener {

    // Medidas Barra
    private int lineX = (getWidth() - 200) / 2;
    private int lineWidth = 200;

    // Dimensões da tela, onde a bola irá listar
    private int largura;
    private int altura;

    // Medidas da bola
    private float raioBall = 40;
    private float diametroBall = raioBall * 2;

    // Cordenadas bola
    private float eixoX = raioBall + 50;
    private float eixoY = raioBall + 20;

    // Direcao bola
    private float direcaoX = 3;
    private float direacoY = 3;
    private Thread thread;
    private Boolean animacao;

    public BallGame() {
        addKeyListener(this);

        setFocusable(true); // Dando foco ao Jpanel para receber eventos do KeyListener
        requestFocus();
        iniciaAnimacao();
    }

    protected void paintComponent(Graphics ball) {
        // desenha bola
        super.paintComponent(ball);
        ball.setColor(Color.RED);
        ball.fillOval((int) (eixoX - raioBall), (int) (eixoY - raioBall), (int) diametroBall, (int) diametroBall);

        // desenha Barra do jogo
        ball.setColor(Color.BLACK);
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

    public void iniciaAnimacao() {
        animacao = true;
        thread = new Thread() {
            public void run() {
                while (animacao) {
                    /* Pegando a dimensões da tela */
                    largura = getWidth();
                    altura = getHeight();

                    eixoX += direcaoX;
                    eixoY += direacoY;

                    if (eixoX - raioBall < 0) {
                        direcaoX = -direcaoX; /* inverte a direção */
                        eixoX = raioBall; /* evitar que saia da tela */
                    } else if (eixoX + raioBall > largura) {
                        direcaoX = -direcaoX;
                        eixoX = largura - raioBall;
                    }

                    if (eixoY - raioBall < 0) {
                        direacoY = -direacoY; /* inverte a direção */
                        eixoY = raioBall; /* evitar que saia da tela */
                    } else if (eixoY + raioBall > altura) {
                        direacoY = -direacoY;
                        eixoY = altura - raioBall;
                    }

                    /* atraso de 50 milissegundos entre cada iteração da animacao da bola */
                    try {
                        Thread.sleep(50);
                        verificaColisaoBall();
                    } catch (InterruptedException ex) {
                        System.out.println("Thread sleep foi interrompida: " + ex.getMessage());
                    }

                    repaint();
                }
            }
        };

        thread.start();
    }

    private void verificaColisaoBall() {
        int ballTop = (int) (eixoY - raioBall);
        int ballBottom = (int) (eixoY + raioBall);
        int lineY = getHeight() - 20;
        int barraBottom = lineY + 40;

        if (ballBottom >= lineY && ballTop <= barraBottom) {
            int ballLeft = (int) (eixoX - raioBall);
            int ballRight = (int) (eixoX + raioBall);
            int barraLeft = lineX;
            int barraRight = lineX + 200;

            if (ballRight >= barraLeft && ballLeft <= barraRight) {
                int opcao = JOptionPane.showOptionDialog(this, "A BOLA ACERTOU A BARRA", "FIM DE JOGO", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                if (opcao == JOptionPane.OK_OPTION || opcao == JOptionPane.CLOSED_OPTION) {
                    new TelaEndGame();
                    SwingUtilities.getWindowAncestor(this).dispose();
                    animacao = false;
                }
            }
        }
    }
}
