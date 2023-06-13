package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Cadastro;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TelaEndGame extends JFrame implements ActionListener, WindowListener{
    private JTable jtTabela;
    private DefaultTableModel tabelaModel;
    private JButton jbVoltar;
    private JButton jbSair;
    private JButton jbSalvar;

    public TelaEndGame(String tipoJogador) {
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
        if (tipoJogador == "adm") {
            jbSalvar = new JButton("SALVAR");
            jbSalvar.addActionListener(this);
            containerButton.add(jbSalvar);
        }
        
        criandoTabela(tipoJogador);
        add(new JScrollPane(jtTabela), BorderLayout.CENTER);
        add(containerButton, BorderLayout.SOUTH);

        addWindowListener(this);

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    private void criandoTabela(String tipoJogador) {
        tabelaModel = new DefaultTableModel(new Object[] { "COD", "NOME", "UF", "PONTOS" }, 0);
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
        if (tipoJogador == "adm") {
            jtTabela.setEnabled(true);
        } else {
            jtTabela.setEnabled(false);
        }
    }

    private void getDadosJtable() {
        int totalRows = jtTabela.getRowCount();
        int numColumns = jtTabela.getColumnCount();
        Cadastro cadastroDadosJt = new Cadastro();
        String dadoString;
        int dadoInt;

        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                switch (j) {
                    case 0:
                        dadoString = (String) jtTabela.getValueAt(i, j);
                        cadastroDadosJt.setCodigoJogador(dadoString);
                        break;
                    case 1:
                        dadoString = (String) jtTabela.getValueAt(i, j);
                        cadastroDadosJt.setNome(dadoString);
                        break;
                    case 2:
                        dadoString = (String) jtTabela.getValueAt(i, j);
                        cadastroDadosJt.setUf(dadoString);
                        break;
                    case 3:
                        dadoString = (String) jtTabela.getValueAt(i, j);
                        dadoInt = Integer.parseInt(dadoString);
                        cadastroDadosJt.setPontos(dadoInt);
                        break;
                    default:
                        Avisos.geraMensagemErro("Coluna inválida. Entre em contato conosco!");
                        break;
                }
            }
        }

        System.out.println(cadastroDadosJt.getNome());
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

        if (e.getSource() == jbSalvar) {
            getDadosJtable();
            Avisos.geraMensagemSucesso(TelaEndGame.this, "Dados salvo com sucesso!");
            new LoginMenu();
            dispose();
        }
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (!Avisos.confirmaSairTelaCadastro(TelaEndGame.this, "Deseja Realmente voltar ao menu principal ?")) {
            return ;
        }
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }
}
