package br.com.digitalxp.controller.internet.principal;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import br.com.digitalxp.controller.internet.ordemservico.CategoriaGettyImage;
import br.com.digitalxp.controller.internet.ordemservico.ImagemGettyImage;
import br.com.digitalxp.gettyImages.GettyImagesAPI;
import br.com.digitalxp.model.gettyimages.RepeatPaginator;


@ManagedBean(name="principalController")
@SessionScoped
public class PrincipalController {

	private ImagemGettyImage imagem = new ImagemGettyImage();
	private List<ImagemGettyImage> listaImagens;
	private List<CategoriaGettyImage> listaCategoria;
	private String busca;
	private RepeatPaginator paginator;

	/*@PostConstruct
	public void init() {
		this.popularimagem();
		this.popularcategoria();
	}*/

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
		listaImagens = GettyImagesAPI.getInstance().search("esporte");
		paginator = new RepeatPaginator(this.listaImagens);
	}
	
	public String buscarImagens(){
		listaImagens = GettyImagesAPI.getInstance().search(busca);
		paginator = new RepeatPaginator(this.listaImagens);
		return "categoria";
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

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public RepeatPaginator getPaginator() {
		return paginator;
	}

	public void setPaginator(RepeatPaginator paginator) {
		this.paginator = paginator;
	}
	
}
