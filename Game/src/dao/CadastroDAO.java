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
    }

    public List<Cadastro> getAllCadastros() {
        List<Cadastro> cadastro = new ArrayList<>();

        try {
            sql = "select ct.id, ps.codigo, ct.nome, ed.uf, ps.pontos from  cadastro as ct left join playerScore as ps on ct.idJogador = ps.id left join endereco as ed on ct.idEndereco = ed.id order by ps.pontos";
            statement = database.getConnection().prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cadastro dadosCadastro = new Cadastro();
                dadosCadastro.setId(resultSet.getString("ct.id"));
                dadosCadastro.setCodigoJogador(resultSet.getString("ps.codigo"));
                dadosCadastro.setNome(resultSet.getString("ct.nome"));
                dadosCadastro.setUf(resultSet.getString("ed.uf"));
                dadosCadastro.setPontos(resultSet.getInt("ps.pontos"));

                cadastro.add(dadosCadastro);

            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cadastro;
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
                    if (resultSet.getInt("ct.isAdm") == 1) {
                        tipoJogador = "adm";
                    } else {
                        tipoJogador = "jogador";
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipoJogador;
    }

    public boolean deleteCadastro(String id) {
        try {
            sql = "delete from cadastro where id = ?";
            statement = database.getConnection().prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}