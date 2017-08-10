package br.com.digitalxp.model;

import java.time.LocalDateTime;

import br.com.digitalxp.repository.entity.ArtistaEntity;

public class ArtistaModel {

	private Integer codigo;
	private String nome;
	private LocalDateTime dataCadastro;
	private UsuarioModel usuarioModel;

	public ArtistaModel() {

	}

	public ArtistaModel(ArtistaEntity artista) {
		this.codigo = artista.getCodigo();
		this.nome = artista.getNome();
		this.dataCadastro = artista.getDataCadastro();
		this.usuarioModel = new UsuarioModel(artista.getUsuarioEntity());
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

}