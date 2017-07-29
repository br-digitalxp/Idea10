package br.com.digitalxp.model;

import java.time.LocalDateTime;

import br.com.digitalxp.repository.entity.CategoriaImagemEntity;

public class CategoriaImagemModel {

	private Integer codigo;
	private String nomeCategoriaImagem;
	private Integer ordemMenu;
	private LocalDateTime dataCadastro;
	private boolean categoriaPrincipal;
	private UsuarioModel usuarioModel;

	public CategoriaImagemModel() {
	}

	public CategoriaImagemModel(CategoriaImagemEntity entity) {
		this.codigo = entity.getCodigo();
		this.nomeCategoriaImagem = entity.getNomeCategoriaImagem();
		this.ordemMenu = entity.getOrdemMenu();
		this.dataCadastro = entity.getDataCadastro();
		this.categoriaPrincipal = entity.isCategoriaPrincipal();
		this.usuarioModel = new UsuarioModel(entity.getUsuarioEntity());
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nomeCategoriaImagem
	 */
	public String getNomeCategoriaImagem() {
		return nomeCategoriaImagem;
	}

	/**
	 * @param nomeCategoriaImagem
	 *            the nomeCategoriaImagem to set
	 */
	public void setNomeCategoriaImagem(String nomeCategoriaImagem) {
		this.nomeCategoriaImagem = nomeCategoriaImagem;
	}

	/**
	 * @return the ordemMenu
	 */
	public Integer getOrdemMenu() {
		return ordemMenu;
	}

	/**
	 * @param ordemMenu
	 *            the ordemMenu to set
	 */
	public void setOrdemMenu(Integer ordemMenu) {
		this.ordemMenu = ordemMenu;
	}

	/**
	 * @return the dataCadastro
	 */
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro
	 *            the dataCadastro to set
	 */
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return the categoriaPrincipal
	 */
	public boolean isCategoriaPrincipal() {
		return categoriaPrincipal;
	}

	/**
	 * @param categoriaPrincipal
	 *            the categoriaPrincipal to set
	 */
	public void setCategoriaPrincipal(boolean categoriaPrincipal) {
		this.categoriaPrincipal = categoriaPrincipal;
	}

	/**
	 * @return the usuarioModel
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/**
	 * @param usuarioModel
	 *            the usuarioModel to set
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

}