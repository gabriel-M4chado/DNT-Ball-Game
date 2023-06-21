package view;

import javax.swing.*;

public class Avisos {

    public static boolean confirmaSairTelaCadastro(JFrame frame, String frase) {
        int opcao = JOptionPane.showConfirmDialog(
                frame,
                frase,
                "ATENÇÃO",
                JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            new LoginMenu();
            frame.dispose();
            return true;
        }
        
        return false;
    }

    public static void geraMensagemErro(String fraseErro) {
        JOptionPane.showMessageDialog(null, fraseErro, "ERRO", JOptionPane.ERROR_MESSAGE);
    }

    public static void geraMensagemSucesso(JFrame frame ,String frase) {
        int opcao = JOptionPane.showOptionDialog(null, frase , null,  JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        
        if (opcao == JOptionPane.OK_OPTION || opcao == JOptionPane.CLOSED_OPTION) {
            if (!frase.contains("excluido")) {
                new LoginMenu();
                frame.dispose();
            }
        }
    }
}
