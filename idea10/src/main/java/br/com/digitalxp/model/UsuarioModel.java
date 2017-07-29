package br.com.digitalxp.model;

import java.io.Serializable;

import br.com.digitalxp.repository.entity.UsuarioEntity;

public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo;
	private String usuario;
	private String senha;

	public UsuarioModel() {
	}

	public UsuarioModel(UsuarioEntity entity) {
		this.codigo = entity.getCodigo();
		this.usuario = entity.getUsuario();
		this.senha = entity.getSenha();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
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

}