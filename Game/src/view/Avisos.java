package view;

import javax.swing.*;

public class Avisos {

    public static void main(JFrame frame) {
        confirmaSairTelaCadastro(frame);      
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
