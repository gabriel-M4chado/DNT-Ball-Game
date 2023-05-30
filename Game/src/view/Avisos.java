package view;

import javax.swing.*;

public class Avisos {

    public static void confirmaSairTelaCadastro(JFrame frame, String frase) {
        int opcao = JOptionPane.showConfirmDialog(
                frame,
                frase,
                "ATENÇÃO",
                JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            new LoginMenu();
            frame.dispose();
        }
    }
}
