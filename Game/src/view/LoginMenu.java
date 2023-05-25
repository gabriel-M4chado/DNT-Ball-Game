package view;

import javax.swing.*;
import java.awt.*;

public class LoginMenu {
    private JFrame telaLogin;
    private JPanel containerTela;
    private JLabel codigoLabel;
    private JTextField codigoField;
    private JButton playGameButton;
    private JButton criaContaButton;

    public LoginMenu(){
        telaLogin = new JFrame("BEM-VINDO");
        telaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        containerTela = new JPanel(new GridBagLayout());

        codigoLabel = new JLabel("Código");
        codigoField = new JTextField(10);

        GridBagConstraints atributosGrid = new GridBagConstraints();
        atributosGrid.fill = GridBagConstraints.HORIZONTAL;
        atributosGrid.insets = new Insets(5, 5, 5, 5);

        atributosGrid.gridx = 0;
        atributosGrid.gridy = 0;
        containerTela.add(codigoLabel, atributosGrid);

        atributosGrid.gridx = 1;
        atributosGrid.gridy = 0;
        containerTela.add(codigoField, atributosGrid);

        playGameButton = new JButton("PLAY");
        playGameButton.setBackground(Color.BLACK);
        playGameButton.setForeground(Color.WHITE);

        atributosGrid.gridx = 2;
        atributosGrid.gridy = 0;
        atributosGrid.anchor = GridBagConstraints.CENTER; /* Aqui determina como um componente é posicionado dentro de tela quando não preenche todo o espaço disponível em tela*/
        containerTela.add(playGameButton,atributosGrid);

        criaContaButton = new JButton("CRIAR CONTA");
        criaContaButton.setBackground(Color.BLACK);
        criaContaButton.setForeground(Color.WHITE);

        atributosGrid.gridx = 2;
        atributosGrid.gridy = 1;
        atributosGrid.anchor = GridBagConstraints.CENTER; 

        containerTela.add(criaContaButton,atributosGrid);

        telaLogin.getContentPane().add(containerTela);

        telaLogin.setSize(400, 300);
        telaLogin.setResizable(false);
        telaLogin.setVisible(true);
        telaLogin.setLocationRelativeTo(null);

    }
}
