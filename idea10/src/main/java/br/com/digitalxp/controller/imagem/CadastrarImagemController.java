package br.com.digitalxp.controller.imagem;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.controller.usuario.UsuarioAdmController;
import br.com.digitalxp.model.ArtistaModel;
import br.com.digitalxp.model.CategoriaImagemModel;
import br.com.digitalxp.model.ImagemModel;
import br.com.digitalxp.repository.ArtistaRepository;
import br.com.digitalxp.repository.CategoriaImagemRepository;
import br.com.digitalxp.repository.ImagemRepository;
import br.com.digitalxp.uteis.Uteis;

@Named(value = "cadastrarImagemController")
@RequestScoped
public class CadastrarImagemController {

	@Inject
	ImagemModel imagemModel;

	@Inject
	UsuarioAdmController usuarioController;

	@Inject
	ImagemRepository ImagemRepository;

	@Inject
	ArtistaRepository artistaRepository;

	private CategoriaImagemModel categoriaImagem;
	private List<SelectItem> listaCategoria;

	private ArtistaModel artista;
	private List<SelectItem> listaArtistas;

	/**
	 * @return the ImagemModel
	 */
	public ImagemModel getImagemModel() {
		return imagemModel;
	}

	/**
	 * @param ImagemModel
	 *            the ImagemModel to set
	 */
	public void setImagemModel(ImagemModel ImagemModel) {
		this.imagemModel = ImagemModel;
	}

	public void setListaCategoria(List<SelectItem> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public List<SelectItem> getListaCategoria() {
		return listaCategoria;
	}

	public CategoriaImagemModel getCategoriaImagem() {
		return categoriaImagem;
	}

	public void setCategoriaImagem(CategoriaImagemModel categoriaImagem) {
		this.categoriaImagem = categoriaImagem;
	}

	public ArtistaModel getArtista() {
		return artista;
	}

	public void setArtista(ArtistaModel artista) {
		this.artista = artista;
	}

	public List<SelectItem> getListaArtistas() {
		return listaArtistas;
	}

	public void setListaArtistas(List<SelectItem> listaArtistas) {
		this.listaArtistas = listaArtistas;
	}

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void SalvarNovaImagem() {

		imagemModel.setUsuario(this.usuarioController.GetUsuarioSession());
		imagemModel.setCategoria(this.categoriaImagem);
		imagemModel.setArtista(this.getArtista());

		ImagemRepository.SalvarNovoRegistro(this.imagemModel);

		Uteis.MensagemInfo("Registro cadastrado com sucesso");

		imagemModel = null;
	}

	@PostConstruct
	public void init() {

		CategoriaImagemRepository categoriaImagemRepository = new CategoriaImagemRepository();
		this.setListaCategoria(new ArrayList<SelectItem>());
		// RETORNAR AS artistas CADASTRADAS
		for (CategoriaImagemModel selectItem : categoriaImagemRepository.GetCategoriaImagem()) {
			this.getListaCategoria().add(new SelectItem(selectItem.getCodigo(), selectItem.getNomeCategoriaImagem()));
		}

		categoriaImagem = new CategoriaImagemModel();

		ArtistaRepository artistaRepository = new ArtistaRepository();
		this.setListaArtistas(new ArrayList<SelectItem>());
		// RETORNAR OS artistas CADASTRADAS
		for (ArtistaModel selectItem : artistaRepository.GetArtistas()) {
			this.getListaArtistas().add(new SelectItem(selectItem.getCodigo(), selectItem.getNome()));
		}

		artista = new ArtistaModel();
	}

}