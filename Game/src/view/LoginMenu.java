package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMenu extends JFrame implements ActionListener{
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

        /* Aqui organiza os botões em uma única linha horizontal*/
        JPanel containerButton = new JPanel(new FlowLayout());
        atributosGrid.gridx = 1;
        atributosGrid.gridy = 1;
        containerTela.add(containerButton, atributosGrid);

        criaContaButton = new JButton("CRIAR CONTA");
        criaContaButton.setBackground(Color.BLACK);
        criaContaButton.setForeground(Color.WHITE);

        criaContaButton.addActionListener(this);
        containerButton.add(criaContaButton);

        playGameButton = new JButton("PLAY");
        playGameButton.setBackground(Color.BLACK);
        playGameButton.setForeground(Color.WHITE);

        playGameButton.addActionListener(this);
        containerButton.add(playGameButton);

        containerTela.add(containerButton,atributosGrid);

        telaLogin.getContentPane().add(containerTela);

        telaLogin.setSize(400, 300);
        telaLogin.setResizable(false);
        telaLogin.setVisible(true);
        telaLogin.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playGameButton) {
            System.out.println("Clicou Play");
        }

        if (e.getSource() == criaContaButton) {
            System.out.println("Clicou Cria Conta");
            telaLogin.dispose();
            TelaCadastro cadastro = new TelaCadastro();
        }
    }

}
