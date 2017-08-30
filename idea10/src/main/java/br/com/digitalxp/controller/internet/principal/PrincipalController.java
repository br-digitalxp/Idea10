package br.com.digitalxp.controller.internet.principal;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.digitalxp.controller.internet.ordemservico.CategoriaGettyImage;
import br.com.digitalxp.controller.internet.ordemservico.GettyImageFilters;
import br.com.digitalxp.controller.internet.ordemservico.GettyImagePaginator;
import br.com.digitalxp.controller.internet.ordemservico.ImagemGettyImage;
import br.com.digitalxp.gettyImages.GettyImagesAPI;
import br.com.digitalxp.model.SubstratoModel;


@ManagedBean(name="principalController")
@SessionScoped
public class PrincipalController {

	private ImagemGettyImage imagem = new ImagemGettyImage();
	private ImagemGettyImage imagemDestaque = new ImagemGettyImage();
	private List<ImagemGettyImage> listaImagens;
	private List<CategoriaGettyImage> listaCategoria;
	private String busca;
	GettyImagePaginator paginator;
	GettyImageFilters filtros = new GettyImageFilters();
	
	private Boolean enabled = false; // + getter/setter
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void toggle() {
	    enabled = !enabled;
	}
		
	public String buscarImagens(){
		buscarImagens(1);
		return "categoria";
	}
	
	private void buscarImagens(int page){
		paginator = GettyImagesAPI.getInstance().search(busca,page,filtros);
		if(paginator.getLista() != null && !paginator.getLista().isEmpty()){
			imagemDestaque = paginator.getLista().get(0);
			paginator.getLista().remove(0);
		}		
	}
	
	public void abrirImagemAlta(ImagemGettyImage imagem) {
		this.imagem = imagem;
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

	public ImagemGettyImage getImagemDestaque() {
		return imagemDestaque;
	}

	public void setImagemDestaque(ImagemGettyImage imagemDestaque) {
		this.imagemDestaque = imagemDestaque;
	}

	public GettyImagePaginator getPaginator() {
		return paginator;
	}

	public void setPaginator(GettyImagePaginator paginator) {
		this.paginator = paginator;
	}
	
	public String proxima(ActionEvent actionEvent){
		int pagina = paginator.getPagina();
		buscarImagens(++pagina);
		return "categoria";
	}
	
	public String anterior(ActionEvent actionEvent){
		int pagina = paginator.getPagina();
		buscarImagens(--pagina);
		return "categoria";
	}

	public GettyImageFilters getFiltros() {
		return filtros;
	}

	public void setFiltros(GettyImageFilters filtros) {
		this.filtros = filtros;
	}
	
}
