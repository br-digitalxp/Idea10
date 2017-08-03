package br.com.digitalxp.controller.categoriaImagem;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.model.CategoriaImagemModel;
import br.com.digitalxp.repository.CategoriaImagemRepository;

@Named(value = "consultarCategoriaImagemController")
@ViewScoped
public class ConsultarCategoriaImagemController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private CategoriaImagemModel categoriaImagemModel;

	@Produces
	private List<CategoriaImagemModel> categoriaImagens;

	@Inject
	transient private CategoriaImagemRepository categoriaImagemRepository;

	/***
	 * CARREGA INFORMAÇÕES DE UMA CategoriaImagem PARA SER EDITADA
	 * 
	 * @param categoriaImagemModel
	 */
	public void Editar(CategoriaImagemModel categoriaImagemModel) {

		this.categoriaImagemModel = categoriaImagemModel;

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		this.categoriaImagemRepository.AlterarRegistro(this.categoriaImagemModel);

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param categoriaImagemModel
	 */
	public void ExcluirCategoriaImagem(CategoriaImagemModel categoriaImagemModel) {

		// EXCLUI A CategoriaImagem DO BANCO DE DADOS
		this.categoriaImagemRepository.ExcluirRegistro(categoriaImagemModel.getCodigo());

		// REMOVENDO A CategoriaImagem DA LISTA
		// ASSIM QUE É A CategoriaImagem É REMOVIDA DA LISTA O DATATABLE É
		// ATUALIZADO
		this.categoriaImagens.remove(categoriaImagemModel);

	}

	/**
	 * @return the categoriaImagens
	 */
	public List<CategoriaImagemModel> getCategoriaImagens() {
		return categoriaImagens;
	}

	/**
	 * @param categoriaImagens
	 *            the categoriaImagens to set
	 */
	public void setCategoriaImagens(List<CategoriaImagemModel> categoriaImagens) {
		this.categoriaImagens = categoriaImagens;
	}

	public CategoriaImagemModel getCategoriaImagemModel() {
		return categoriaImagemModel;
	}

	public void setCategoriaImagemModel(CategoriaImagemModel categoriaImagemModel) {
		this.categoriaImagemModel = categoriaImagemModel;
	}

	/***
	 * CARREGA AS CategoriaImagemS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {

		// RETORNAR AS CategoriaImagemS CADASTRADAS
		this.categoriaImagens = categoriaImagemRepository.GetCategoriaImagem();
	}

}