package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEndGame extends JFrame implements ActionListener{
    private JTable jtTabela;
    private DefaultTableModel tabelaModel;
    private JButton jbVoltar;
    private JButton jbSair;

    public TelaEndGame() {
        setTitle("RESULTADO");
        setLayout(new BorderLayout());
        setSize(600, 200);

        jbVoltar = new JButton("VOLTAR");
        jbVoltar.addActionListener(this);

        jbSair = new JButton("SAIR");
        jbSair.addActionListener(this);

        JPanel containerButton = new JPanel();
        containerButton.add(jbVoltar);
        containerButton.add(jbSair);

        criandoTabela();
        add(new JScrollPane(jtTabela), BorderLayout.CENTER);
        add(containerButton, BorderLayout.SOUTH);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbVoltar) {
            new LoginMenu();
            dispose();
        }

        if (e.getSource() == jbSair) {
            dispose();
        }
    }
}
