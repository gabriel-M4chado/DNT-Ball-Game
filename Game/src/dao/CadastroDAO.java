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
            sql = "select * from cadastro where isAdm != ?";
            statement = database.getConnection().prepareStatement(sql);
            statement.setBoolean(1, true);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String cadastroNome = resultSet.getString("nome");

                clients.add(cadastroNome);

            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public String vericaCodigo(String codigo) {
        String tipoJogador = "";
        try {
            sql = "select ps.id, ct.isAdm from playerScore as ps inner join cadastro as ct on ps.id = ct.idJogador where ps.codigo = ? order by ps.id";
            statement = database.getConnection().prepareStatement(sql);
            statement.setString(1, codigo);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("ps.id") > 0) {
                    if(resultSet.getInt("ct.isAdm") == 1){
                        tipoJogador =  "adm";
                    }else {
                        tipoJogador = "jogador";
                    }
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipoJogador;
    }
}