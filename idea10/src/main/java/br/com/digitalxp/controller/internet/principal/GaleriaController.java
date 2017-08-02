package br.com.digitalxp.controller.internet.principal;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.digitalxp.controller.internet.ordemservico.ImagemGettyImage;

@Named(value = "galeriaController")
@RequestScoped
public class GaleriaController {

	private ImagemGettyImage imagem;
	private List<ImagemGettyImage> listaImagens;

	public String iniciarPagina(ImagemGettyImage imagem) {

		return "";
	}

	public List<ImagemGettyImage> getListaImagens() {
		return listaImagens;
	}

	public void setListaImagens(List<ImagemGettyImage> listaImagens) {
		this.listaImagens = listaImagens;
	}

	public ImagemGettyImage getImagem() {
		return imagem;
	}

	public void setImagem(ImagemGettyImage imagem) {
		this.imagem = imagem;
	}

}
