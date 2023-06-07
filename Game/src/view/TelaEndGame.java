package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaEndGame extends JFrame {
    private JTable jtTabela;
    private DefaultTableModel tabelaModel;

    public TelaEndGame() {
        setTitle("RESULTADO");
        setLayout(new BorderLayout());
        setSize(600, 200);

        criandoTabela();
        add(new JScrollPane(jtTabela), BorderLayout.CENTER);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void criandoTabela() {
        tabelaModel = new DefaultTableModel(new Object[] { "COD", "NOME", "UF", "TEMPO" }, 0);
        tabelaModel.setRowCount(0);

        String[][] dadosTabela = {
                { "000", "João", "RS", "150"},
                { "001", "KySer", "MG", "0"},
                { "002", "Kevin" , "SP", "1"}
        };

        for (String[] value : dadosTabela) {
            tabelaModel.addRow(value);
        }

        jtTabela = new JTable(tabelaModel);
        jtTabela.setModel(tabelaModel);
        jtTabela.setVisible(true);
    }
}
