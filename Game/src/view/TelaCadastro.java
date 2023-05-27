package view;

import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaCadastro extends JFrame implements ActionListener {

    private JFrame telaCadastro;
    private JPanel containerTela;
    private JLabel lblNome;
    private JTextField jtfNome;
    private JLabel lblEmail;
    private JTextField jtfEmail;
    private JLabel lblCode;
    private JTextField jtfCode;

    public TelaCadastro() {
        this.telaCadastro = new JFrame("Cadastro");

        telaCadastro.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmaSairTelaCadastro(telaCadastro);
            }
        });

        containerTela = formCadastro();

        telaCadastro.setContentPane(containerTela);
        telaCadastro.setSize(480, 600);
        telaCadastro.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        telaCadastro.setResizable(false);
        telaCadastro.setVisible(true);
        telaCadastro.setLocationRelativeTo(null);
    }

    public JPanel formCadastro() {

        containerTela = new JPanel();
        containerTela.setLayout(new GridBagLayout());

        GridBagConstraints atributosGrid = new GridBagConstraints();
        atributosGrid.anchor = GridBagConstraints.WEST;
        atributosGrid.insets = new Insets(15, 5, 5, 5);

        lblNome = new JLabel("Nome:");
        jtfNome = new JTextField(21);

        atributosGrid.gridx = 0;
        atributosGrid.gridy = 0;
        containerTela.add(lblNome, atributosGrid);

        atributosGrid.gridx = 1;
        atributosGrid.gridy = 0;
        containerTela.add(jtfNome, atributosGrid);

        lblEmail = new JLabel("E-mail:");
        jtfEmail = new JTextField(21);

        atributosGrid.gridx = 0;
        atributosGrid.gridy = 1;
        containerTela.add(lblEmail, atributosGrid);

        atributosGrid.gridx = 1;
        atributosGrid.gridy = 1;
        containerTela.add(jtfEmail, atributosGrid);

        lblCode = new JLabel("Code:");
        jtfCode = new JTextField(8);

        atributosGrid.gridx = 2;
        atributosGrid.gridy = 1;
        containerTela.add(lblCode, atributosGrid);

        atributosGrid.gridx = 3;
        atributosGrid.gridy = 1;
        containerTela.add(jtfCode, atributosGrid);

        atributosGrid.gridx = 0;
        atributosGrid.gridy = 3;
        atributosGrid.weighty = 1.0;
        atributosGrid.anchor = GridBagConstraints.NORTH;
        containerTela.add(Box.createVerticalGlue(), atributosGrid);

        return containerTela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private static void confirmaSairTelaCadastro(JFrame frame) {
        int opcao = JOptionPane.showConfirmDialog(
                frame,
                "Você não finalizou o cadastro, tem certeza que deseja sair ?",
                "ATENÇÃO",
                JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            new LoginMenu();
            frame.dispose();
        }
    }

}
