package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cadastro;

public class CadastroDAO {
    private databaseDAO database;

    public CadastroDAO() {
        database = new ConexaoSqlDAO();
        database.connectBD("localhost", "root", "****", "SUCOS");
        System.out.println(getAllCadastros());
    }

    public List<String> getAllCadastros() {
        List<String> clients = new ArrayList<>();

        try {
            String sql = "SELECT * FROM TABELA_DE_VENDEDORES";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String clientName = resultSet.getString("NOME");

/*                 Cadastro cadastro = new Cadastro();
                cadastro.setNome(clientName); */
                clients.add(clientName);
                
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }
}