package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cadastro;
import view.Avisos;

public class CadastroDAO {
    private databaseDAO database;
    private String sql = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    public CadastroDAO() {
        database = new ConexaoSqlDAO();
        database.connectBD("localhost", "root", "!BD23gjs@#", "dntBallGame");
    }

    public List<Cadastro> getAllCadastros() {
        List<Cadastro> cadastro = new ArrayList<>();

        try {
            sql = "select ct.id, ps.codigo, ct.nome, ed.uf, ps.pontos from  cadastro as ct left join playerScore as ps on ct.idJogador = ps.id left join endereco as ed on ct.idEndereco = ed.id order by ps.pontos desc";
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

    public void updateScore(String codigoJogador, int pontos) {
        try {
            sql = "update playerScore set pontos = ? where codigo = ?";
            statement = database.getConnection().prepareStatement(sql);
            statement.setInt(1, pontos);
            statement.setString(2, codigoJogador);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateCadastro(String id, String codigoJogador, String nome, String uf, int pontos) {
        try {
            String sqlEndereco = "select ende.id from endereco as ende where ende.uf = '" + uf + "'";
            String set = "set c.nome = '"+ nome + "', ps.codigo = '"+ codigoJogador +"', ps.pontos = "+ pontos +", c.idEndereco = (" + sqlEndereco + ")";
            String where = " where c.id = " + Integer.parseInt(id);
            sql = "update cadastro as c inner join playerScore as ps on ps.id = c.idJogador inner join endereco as ed on ed.id = c.idEndereco " + set + where;
            statement = database.getConnection().prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean inserePlayer(int pontos, String codigoJogador) {
        try {
            sql = "insert into playerScore values (default, default, ?, ?)";

            statement = database.getConnection().prepareStatement(sql);
            statement.setInt(1, pontos);
            statement.setString(2, codigoJogador);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public String retornaIdJogador(String codigo) {
        String id = "";
        try {
            statement = database.getConnection().prepareStatement("select id from playerScore where codigo = ? limit 1");
            statement.setString(1, codigo);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public Boolean emailExist(String email) {
        try {
            statement = database.getConnection().prepareStatement("select id from cadastro where email = ? limit 1");
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveNewCadastro(String nome, String email, String sexo, String rua, String uf, int pontos, String codigoJogador) {
        try {
            inserePlayer(pontos, codigoJogador);
            String id = retornaIdJogador(codigoJogador);
            String sqlEndereco = "(select ende.id from endereco as ende where ende.uf like ('" + uf + "'))";
            sql = "insert into cadastro values (default, ?, ?, ?, ?, default, "+ sqlEndereco +", ?)";
            statement = database.getConnection().prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setString(3, sexo);
            statement.setString(4, rua);
            statement.setString(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}