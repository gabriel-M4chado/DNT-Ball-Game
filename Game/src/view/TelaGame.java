package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class TelaGame{
    private JFrame jfTelaGame;

    public TelaGame() {
        jfTelaGame = new JFrame("Game");
        BallGame ball = new BallGame();

        jfTelaGame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ball.stopAnimacao(true);
                if(!Avisos.confirmaSairTelaCadastro(jfTelaGame,"Deseja realmente sair do Game ?")) {
                    ball.stopAnimacao(false);
                }else{
                    jfTelaGame.getContentPane().remove(ball);
                    jfTelaGame.dispose();
                }
            }
        });

        
        jfTelaGame.getContentPane().add(ball);
        jfTelaGame.setSize(400, 300);
        jfTelaGame.setResizable(false);
        jfTelaGame.setVisible(true);
        jfTelaGame.setDefaultCloseOperation(0); /* DO_NOTHING_ON_CLOSE */
        jfTelaGame.setLocationRelativeTo(null);
    }

}
