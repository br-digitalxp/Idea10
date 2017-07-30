package br.com.digitalxp.controller.imagem;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.model.CategoriaImagemModel;
import br.com.digitalxp.model.ImagemModel;
import br.com.digitalxp.repository.CategoriaImagemRepository;
import br.com.digitalxp.repository.ImagemRepository;

@Named(value = "consultarImagemController")
@ViewScoped
public class ConsultarImagemController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private ImagemModel imagemModel;

	@Produces
	private List<ImagemModel> imagens;

	@Inject
	transient private ImagemRepository imagemRepository;

	private List<CategoriaImagemModel> listaCategoria;

	/***
	 * CARREGA INFORMAÇÕES DE UMA Imagem PARA SER EDITADA
	 * 
	 * @param ImagemModel
	 */
	public void Editar(ImagemModel ImagemModel) {

		this.imagemModel = ImagemModel;

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		this.imagemRepository.AlterarRegistro(this.imagemModel);

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param ImagemModel
	 */
	public void ExcluirImagem(ImagemModel ImagemModel) {

		// EXCLUI A Imagem DO BANCO DE DADOS
		this.imagemRepository.ExcluirRegistro(ImagemModel.getCodigo());

		// REMOVENDO A Imagem DA LISTA
		// ASSIM QUE É A Imagem É REMOVIDA DA LISTA O DATATABLE É
		// ATUALIZADO
		this.imagens.remove(ImagemModel);

	}

	public List<ImagemModel> getImagens() {
		return imagens;
	}

	public void setImagens(List<ImagemModel> imagens) {
		this.imagens = imagens;
	}

	public ImagemModel getImagemModel() {
		return imagemModel;
	}

	public void setImagemModel(ImagemModel ImagemModel) {
		this.imagemModel = ImagemModel;
	}

	public List<CategoriaImagemModel> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<CategoriaImagemModel> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	/***
	 * CARREGA AS ImagemS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {
		CategoriaImagemRepository categoriaImagemRepository = new CategoriaImagemRepository();

		// RETORNAR AS CategoriaImagemS CADASTRADAS
		this.listaCategoria = categoriaImagemRepository.GetCategoriaImagem();
		// RETORNAR AS ImagemS CADASTRADAS
		this.imagens = imagemRepository.GetImagem();
	}

}