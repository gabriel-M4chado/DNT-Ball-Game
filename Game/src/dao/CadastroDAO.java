package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cadastro;

public class CadastroDAO {
    private databaseDAO database;
    private String sql;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public CadastroDAO() {
        database = new ConexaoSqlDAO();
        database.connectBD("localhost", "root", "****", "dntBallGame");
        System.out.println(getAllCadastros());
    }

    public List<String> getAllCadastros() {
        List<String> clients = new ArrayList<>();

        try {
            sql = "select * from cadastro";
            statement = database.getConnection().prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String clientName = resultSet.getString("nome");

                /*
                 * Cadastro cadastro = new Cadastro();
                 * cadastro.setNome(clientName);
                 */
                clients.add(clientName);

            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public boolean vericaCodigo(String codigo) {
        try {
            statement = database.getConnection()
                    .prepareStatement("select id from playerScore as ps where ps.codigo = ?");
            statement.setString(1, codigo);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt("id") > 0) {
                    return true;
                }
            }
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}