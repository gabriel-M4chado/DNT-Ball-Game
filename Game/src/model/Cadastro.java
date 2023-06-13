package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dao.CadastroDAO;

public class Cadastro {
    private String nome;
    private String email;
    private String codigoJogador;
    private String sexo;
    private String uf;
    private String complemento;
    private int pontos;
    private CadastroDAO cadastroDAO;

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
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
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

    public void atualizaCadastros(Cadastro dadosCadastro){
        cadastroDAO = new CadastroDAO();
    }

    public List<String> getAllCadastros(){
        return cadastroDAO.getAllCadastros();
    }
}
