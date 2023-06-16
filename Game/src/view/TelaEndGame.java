package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import dao.CadastroDAO;
import model.Cadastro;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.util.List;

public class TelaEndGame extends JFrame implements ActionListener, WindowListener{
    private JTable jtTabela;
    private DefaultTableModel tabelaModel;
    private JButton jbVoltar;
    private JButton jbSair;
    private JButton jbSalvar;
    private JButton jbExcluir;
    private Cadastro cadastroDadosJt;
    private String tipoJogador;

    public TelaEndGame(String tipoJogador, String codigo, int pontos) {
        this.tipoJogador = tipoJogador;
        cadastroDadosJt = new Cadastro();
        cadastroDadosJt.setCodigoJogador(codigo);
        cadastroDadosJt.setPontos(pontos);
        cadastroDadosJt.atualizaPlacar();
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

            jbExcluir = new JButton("EXCLUIR");
            jbExcluir.addActionListener(this);
            containerButton.add(jbExcluir);
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
        if(tipoJogador == "adm") {
            tabelaModel = new DefaultTableModel(new Object[] {"ID" ,"CÓDIGO", "NOME", "UF", "PONTOS" }, 0);
        }else {
            tabelaModel = new DefaultTableModel(new Object[] {"NOME", "UF", "PONTOS" }, 0);
        }
        
        tabelaModel.setRowCount(0);

        CadastroDAO cadastroDao = new CadastroDAO();
        List<Cadastro> dados= cadastroDao.getAllCadastros();
        String[][] dadosTabela = new String[dados.size()][5];

        for (int i = 0; i < dados.size(); i++) {
            Cadastro data = dados.get(i);
            dadosTabela[i][0] = data.getId();
            dadosTabela[i][1] = data.getCodigoJogador();
            dadosTabela[i][2] = data.getNome();
            dadosTabela[i][3] = data.getUf();
            dadosTabela[i][4] = String.valueOf(data.getPontos());
        }

        if (tipoJogador != "adm") {
            for (String[] value : dadosTabela) {
                String [] dadosApartirdaDoNome = Arrays.copyOfRange( value, 2, value.length);
                tabelaModel.addRow(dadosApartirdaDoNome);
            }

        } else {
            for (String[] value : dadosTabela) {
                tabelaModel.addRow(value);
            }
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

    private Boolean getDadosJtableAdm() {
        int totalRows = jtTabela.getRowCount();
        int numColumns = jtTabela.getColumnCount();
        cadastroDadosJt = new Cadastro();
        int dadoInt;

        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                switch (j) {
                    case 0:
                        cadastroDadosJt.setId(jtTabela.getValueAt(i, j).toString());
                        break;
                    case 1:
                        cadastroDadosJt.setCodigoJogador(jtTabela.getValueAt(i, j).toString());
                        break;
                    case 2:
                        cadastroDadosJt.setNome(jtTabela.getValueAt(i, j).toString());
                        break;
                    case 3:
                        cadastroDadosJt.setUf(jtTabela.getValueAt(i, j).toString());
                        break;
                    case 4:
                        cadastroDadosJt.setPontos(Integer.parseInt(jtTabela.getValueAt(i, j).toString()));
                        break;
                    default:
                        Avisos.geraMensagemErro("Coluna inválida. Entre em contato conosco!");
                        break;
                }
            }

            if (!cadastroDadosJt.atualizaCadastros()) {
                return false;
            }
        }

        return true;
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
            if (getDadosJtableAdm()) {
                JOptionPane.showMessageDialog(null, "Operation successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }else{
                Avisos.geraMensagemErro("Erro ao salvar, entre em contato conosco!");
            }

        }

        if (e.getSource() == jbExcluir) {
            int[] selecionaRows = jtTabela.getSelectedRows();
            for (int selectedRow : selecionaRows) {
                if (selectedRow >=0 && selectedRow < jtTabela.getRowCount()) {
                Object idSelecionado = jtTabela.getValueAt(selectedRow, 0);
                    if (idSelecionado != null) {
                        cadastroDadosJt = new Cadastro();
                        cadastroDadosJt.setId(idSelecionado.toString());
                        if (cadastroDadosJt.removeCadastro()) {
                            tabelaModel.removeRow(selectedRow);
                            Avisos.geraMensagemSucesso(TelaEndGame.this, "Cadastro excluido com sucesso!");
                        }
                    }
                }
            }
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
