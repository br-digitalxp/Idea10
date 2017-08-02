package br.com.digitalxp.controller.internet.ordemservico;

import br.com.digitalxp.model.ImagemModel;

public class ImagemGettyImage {

	private ImagemModel imagem;
	private String titulo;
	private String descricao;
	private String miniDescricao;
	private CategoriaGettyImage categoria;

	public ImagemModel getImagem() {
		return imagem;
	}

	public void setImagem(ImagemModel imagem) {
		this.imagem = imagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CategoriaGettyImage getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaGettyImage categoria) {
		this.categoria = categoria;
	}

	public String getMiniDescricao() {
		return miniDescricao;
	}

	public void setMiniDescricao(String miniDescricao) {
		this.miniDescricao = miniDescricao;
	}

}
