package br.com.digitalxp.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.digitalxp.repository.entity.UsuarioEntity;

public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int codigo;
	private String usuario;
	private String senha;
	private boolean ativo;
	private LocalDateTime dataCadastro;

	public UsuarioModel() {
	}

	public UsuarioModel(UsuarioEntity entity) {
		this.codigo = entity.getCodigo();
		this.usuario = entity.getUsuario();
		this.senha = entity.getSenha();
		this.ativo = entity.isAtivo();
		this.dataCadastro = entity.getDataCadastro();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}