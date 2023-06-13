package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import view.Avisos;

public class ConexaoSqlDAO implements databaseDAO {
    private Connection conexaoBD;
    private String jdbcURL;

    @Override
    public void connectBD(String ip, String username, String password, String databaseName) {
        try {
            jdbcURL = "jdbc:mysql://localhost:3306/" + databaseName;
            conexaoBD = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Conectado ao banco!");
        } catch (SQLException e) {
            System.out.println("Falha ao conectar com o banco de dados: " + e.getMessage());
            Avisos.geraMensagemErro("Falha ao conectar ao jogo");
        }
    }

    @Override
    public void disconnect() {
        try {
            if (conexaoBD != null && !((ConexaoSqlDAO) conexaoBD).isConnected()) {
                conexaoBD.close();
                System.out.println("Banco desconectado!");
            }
        } catch (SQLException e) {
            System.err.println("Falha ao disconectar com o banco de dados: " + e.getMessage());
            Avisos.geraMensagemErro("Falha ao desconectar");
        }
    }

    @Override
    public boolean isConnected() {
        try {
            return conexaoBD != null && !conexaoBD.isClosed();
        } catch (SQLException e) {
            Avisos.geraMensagemErro("Falha ao conectar");
            System.err.println("Erro ao verificar a conexao, status: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getJdbcUrl() {
        return jdbcURL;
    }

    @Override
    public Connection getConnection() {
        return conexaoBD;
    }
}
