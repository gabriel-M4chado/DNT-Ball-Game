package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TelaCadastro extends JFrame implements ActionListener{

    private JFrame telaCadastro;
    
    public TelaCadastro(){
        this.telaCadastro = new JFrame("Cadastro");

        telaCadastro.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmaSairTelaCadastro(telaCadastro);
            }
        });

        telaCadastro.setSize(450, 600);
        telaCadastro.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        telaCadastro.setResizable(false);
        telaCadastro.setVisible(true);
        telaCadastro.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private static void confirmaSairTelaCadastro(JFrame frame) {
        int opcao = JOptionPane.showConfirmDialog(
                frame,
                "Você não finalizou o cadastro, tem certeza que deseja sair ?",
                "Saindo da tela de cadastro",
                JOptionPane.YES_NO_OPTION
        );

        if (opcao == JOptionPane.YES_OPTION) {
            new LoginMenu();
            frame.dispose(); 
        }
    }

}
