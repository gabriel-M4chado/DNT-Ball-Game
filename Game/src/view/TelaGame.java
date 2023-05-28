package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class TelaGame{
    private JFrame jfTelaGame;

    public TelaGame() {
        jfTelaGame = new JFrame("Game");

        jfTelaGame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Avisos.main(jfTelaGame,"Deseja realmente sair do Game ?");
            }
        });

        jfTelaGame.setSize(400, 300);
        jfTelaGame.setResizable(false);
        jfTelaGame.setVisible(true);
        jfTelaGame.setDefaultCloseOperation(0); /* DO_NOTHING_ON_CLOSE */
        jfTelaGame.setLocationRelativeTo(null);
    }

}
