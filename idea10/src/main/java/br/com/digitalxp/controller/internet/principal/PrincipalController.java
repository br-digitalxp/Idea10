package br.com.digitalxp.controller.internet.principal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.digitalxp.controller.internet.ordemservico.CategoriaGettyImage;
import br.com.digitalxp.controller.internet.ordemservico.ImagemGettyImage;
import br.com.digitalxp.model.ImagemModel;

@Named(value = "principalController")
@RequestScoped
public class PrincipalController {

	private ImagemGettyImage imagem = new ImagemGettyImage();
	private List<ImagemGettyImage> listaImagens;
	private List<CategoriaGettyImage> listaCategoria;

	@PostConstruct
	public void init() {
		this.popularimagem();
		this.popularcategoria();
	}

	private void popularcategoria() {
		CategoriaGettyImage categoria = new CategoriaGettyImage();
		categoria.setCaminhoImagemDestaque("img/img-produto-1.jpg");
		categoria.setNomeCategria("Praia");

		CategoriaGettyImage categoria2 = new CategoriaGettyImage();
		categoria2.setCaminhoImagemDestaque("img/img-produto-2.jpg");
		categoria2.setNomeCategria("Pet Que Amamos");

		CategoriaGettyImage categoria3 = new CategoriaGettyImage();
		categoria3.setCaminhoImagemDestaque("img/img-produto-3.jpg");
		categoria3.setNomeCategria("Artes de Rua");
		this.listaCategoria = new ArrayList<CategoriaGettyImage>();
		listaCategoria.add(categoria);
		listaCategoria.add(categoria2);
		listaCategoria.add(categoria3);
	}

	private void popularimagem() {
		listaImagens = new ArrayList<ImagemGettyImage>();

		ImagemModel imagemModel = new ImagemModel();
		imagemModel.setCaminhoImagem("img/img-produto-4.jpg");
		this.imagem.setImagem(imagemModel);
		this.imagem.setTitulo("Janela em uma Casa de Campo");
		this.imagem.setDescricao(
				"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English.");
		this.imagem.setMiniDescricao("Janela em uma Casa de Campo");
		CategoriaGettyImage cgi = new CategoriaGettyImage();
		cgi.setNomeCategria("Casas");
		this.imagem.setCategoria(cgi);

		listaImagens.add(imagem);

		ImagemGettyImage imagem2 = new ImagemGettyImage();

		ImagemModel imagemModel2 = new ImagemModel();
		imagemModel2.setCaminhoImagem("img/img-produto-5.jpg");
		imagem2.setImagem(imagemModel2);
		imagem2.setTitulo("Igreja na frança");
		imagem2.setDescricao(
				"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English.");
		imagem2.setMiniDescricao("Janela em uma Casa de Campo");
		CategoriaGettyImage cgi2 = new CategoriaGettyImage();
		cgi2.setNomeCategria("Castelos");
		imagem2.setCategoria(cgi2);

		listaImagens.add(imagem2);

		ImagemGettyImage imagem3 = new ImagemGettyImage();

		ImagemModel imagemModel3 = new ImagemModel();
		imagemModel3.setCaminhoImagem("img/img-produto-6.jpg");
		imagem3.setImagem(imagemModel3);
		imagem3.setTitulo("Passaro Azul");
		imagem3.setDescricao(
				"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English.");
		imagem3.setMiniDescricao("Janela em uma Casa de Campo");
		CategoriaGettyImage cgi3 = new CategoriaGettyImage();
		cgi3.setNomeCategria("Passaros");
		imagem3.setCategoria(cgi3);

		listaImagens.add(imagem3);
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

	public List<CategoriaGettyImage> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<CategoriaGettyImage> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

}
