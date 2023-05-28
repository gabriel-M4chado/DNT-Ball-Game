package view;

import javax.swing.*;

import java.awt.Color;
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
    private JLabel lblSexo;
    private JComboBox<String> selectSexo;
    private JLabel lblUf;
    private JComboBox<String> selectUf;
    private JLabel lblRua;
    private JTextField jtfRua;
    private JButton jbSalvar;
    private JButton jbCancelar;

    public TelaCadastro() {
        this.telaCadastro = new JFrame("Cadastro");

        telaCadastro.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Avisos.main(telaCadastro);
            }
        });

        containerTela = formCadastro();

        telaCadastro.setContentPane(containerTela);
        telaCadastro.setSize(550, 230);
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
        jtfNome = new JTextField(20);

        atributosGrid.gridx = 0;
        atributosGrid.gridy = 0;
        containerTela.add(lblNome, atributosGrid);

        atributosGrid.gridx = 1;
        atributosGrid.gridy = 0;
        containerTela.add(jtfNome, atributosGrid);

        lblSexo = new JLabel("Sexo:");
        selectSexo = new JComboBox<>(new String[]{"Masculino", "Feminino"});

        atributosGrid.gridx = 2;
        atributosGrid.gridy = 0;
        containerTela.add(lblSexo, atributosGrid);

        atributosGrid.gridx = 3;
        atributosGrid.gridy = 0;
        containerTela.add(selectSexo, atributosGrid);

        lblEmail = new JLabel("E-mail:");
        jtfEmail = new JTextField(20);

        atributosGrid.gridx = 0;
        atributosGrid.gridy = 1;
        containerTela.add(lblEmail, atributosGrid);

        atributosGrid.gridx = 1;
        atributosGrid.gridy = 1;
        atributosGrid.gridwidth = 3;
        containerTela.add(jtfEmail, atributosGrid);

        lblCode = new JLabel("Code:");
        jtfCode = new JTextField(8);

        atributosGrid.gridx = 2;
        atributosGrid.gridy = 1;
        atributosGrid.gridwidth = 1;
        containerTela.add(lblCode, atributosGrid);

        atributosGrid.gridx = 3;
        atributosGrid.gridy = 1;
        atributosGrid.gridwidth = 2;
        containerTela.add(jtfCode, atributosGrid);

        lblRua = new JLabel("Rua:");
        jtfRua = new JTextField(20);

        atributosGrid.gridx = 0;
        atributosGrid.gridy = 2;
        containerTela.add(lblRua, atributosGrid);

        atributosGrid.gridx = 1;
        atributosGrid.gridy = 2;
        containerTela.add(jtfRua, atributosGrid);

        lblUf = new JLabel("UF:");
        selectUf = new JComboBox<>((new String[] { "", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "SC", "SE", "SP", "TO" }));

        atributosGrid.gridx = 2;
        atributosGrid.gridy = 2;
        containerTela.add(lblUf, atributosGrid);

        atributosGrid.gridx = 3;
        atributosGrid.gridy = 2;
        containerTela.add(selectUf, atributosGrid);

        jbCancelar = new JButton("CANCELAR");
        jbCancelar.addActionListener(this);

        jbCancelar.setBackground(Color.WHITE);
        jbCancelar.setForeground(Color.BLACK);
        atributosGrid.gridx = 0;
        atributosGrid.gridy = 4;
        atributosGrid.anchor = GridBagConstraints.CENTER;
        containerTela.add(jbCancelar, atributosGrid);

        jbSalvar = new JButton("SALVAR");
        jbSalvar.addActionListener(this);

        jbSalvar.setBackground(Color.BLACK);
        jbSalvar.setForeground(Color.WHITE);
        atributosGrid.gridx = 1;
        atributosGrid.gridy = 4;
        atributosGrid.anchor = GridBagConstraints.LINE_END;
        containerTela.add(jbSalvar, atributosGrid);

        return containerTela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbCancelar) {
            System.out.println("Clicou em Cancelar");
            confirmaSairTelaCadastro(telaCadastro);
        }
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
