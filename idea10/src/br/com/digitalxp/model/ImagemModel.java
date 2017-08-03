package br.com.digitalxp.model;

import java.time.LocalDateTime;

import br.com.digitalxp.repository.entity.ImagemEntity;

public class ImagemModel {

	private Integer codigo;
	private Integer autor;
	private String caminhoImagem;
	private LocalDateTime dataCadastro;
	private boolean exclusivo;
	private CategoriaImagemModel categoria;
	private UsuarioModel usuario;

	public ImagemModel() {

	}

	public ImagemModel(ImagemEntity entity) {
		this.codigo = entity.getCodigo();
		this.autor = entity.getAutor();
		this.caminhoImagem = entity.getCaminhoImagem();
		this.dataCadastro = entity.getDataCadastro();
		this.exclusivo = entity.isExclusivo();
		this.categoria = new CategoriaImagemModel(entity.getCategoria());
		this.usuario = new UsuarioModel(entity.getUsuarioEntity());
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
	 * @return the autor
	 */
	public Integer getAutor() {
		return autor;
	}

	/**
	 * @param autor
	 *            the autor to set
	 */
	public void setAutor(Integer autor) {
		this.autor = autor;
	}

	/**
	 * @return the caminhoImagem
	 */
	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	/**
	 * @param caminhoImagem
	 *            the caminhoImagem to set
	 */
	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
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
	 * @return the exclusivo
	 */
	public boolean getExclusivo() {
		return exclusivo;
	}

	/**
	 * @param exclusivo
	 *            the exclusivo to set
	 */
	public void setExclusivo(boolean exclusivo) {
		this.exclusivo = exclusivo;
	}

	/**
	 * @return the categoria
	 */
	public CategoriaImagemModel getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria
	 *            the categoria to set
	 */
	public void setCategoria(CategoriaImagemModel categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the usuario
	 */
	public UsuarioModel getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

}