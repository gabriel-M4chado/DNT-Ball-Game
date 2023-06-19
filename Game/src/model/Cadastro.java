package model;

import java.util.Objects;

import dao.CadastroDAO;

public class Cadastro {
    private String id;
    private String nome;
    private String email;
    private String codigoJogador;
    private String sexo;
    private String uf;
    private String rua;
    private int pontos;
    private CadastroDAO cadastroDAO;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCodigoJogador() {
        return codigoJogador;
    }
    public void setCodigoJogador(String codigoJogador) {
        this.codigoJogador = codigoJogador;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public int getPontos() {
        return pontos;
    }
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    @Override
	public int hashCode() {
		return Objects.hash(codigoJogador);
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cadastro other = (Cadastro) obj;
		return codigoJogador == other.codigoJogador;
	}

    public void atualizaPlacar(){
        cadastroDAO = new CadastroDAO();
        cadastroDAO.updateScore(getCodigoJogador(), getPontos());
    }

    public boolean atualizaCadastros(){
        cadastroDAO = new CadastroDAO();
        return cadastroDAO.updateCadastro(getId(), getCodigoJogador(), getNome(), getUf(), getPontos());
    }

    public boolean removeCadastro(){
        cadastroDAO = new CadastroDAO();
        return cadastroDAO.deleteCadastro(getId());
    }

    public boolean save() {
        cadastroDAO = new CadastroDAO();
        return cadastroDAO.saveCadastro(getNome(), getEmail(), getSexo(), getRua(), getUf(), getPontos(), getCodigoJogador());
    }
}
