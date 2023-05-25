package view;

import javax.swing.*;
import javax.swing.JFrame;

public class LoginMenu {
    private JFrame telaLogin;

    public LoginMenu(){
        telaLogin = new JFrame("BEM-VINDO");
        telaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        telaLogin.setSize(400, 300);
        telaLogin.setResizable(false);
        telaLogin.setVisible(true);
        telaLogin.setLocationRelativeTo(null);
    }
    
}
