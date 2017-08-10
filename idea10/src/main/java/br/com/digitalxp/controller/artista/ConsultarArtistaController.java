package br.com.digitalxp.controller.artista;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.model.ArtistaModel;
import br.com.digitalxp.repository.ArtistaRepository;

@Named(value = "consultarArtistaController")
@ViewScoped
public class ConsultarArtistaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private ArtistaModel ArtistaModel;

	@Produces
	private List<ArtistaModel> Artistas;

	@Inject
	transient private ArtistaRepository artistaRepository;

	/***
	 * CARREGA INFORMAÇÕES DE UMA Artista PARA SER EDITADA
	 * 
	 * @param ArtistaModel
	 */
	public void Editar(ArtistaModel ArtistaModel) {

		this.ArtistaModel = ArtistaModel;

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		this.artistaRepository.AlterarRegistro(this.ArtistaModel);

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param ArtistaModel
	 */
	public void ExcluirArtista(ArtistaModel ArtistaModel) {

		// EXCLUI A Artista DO BANCO DE DADOS
		this.artistaRepository.ExcluirRegistro(ArtistaModel.getCodigo());

		// REMOVENDO A Artista DA LISTA
		// ASSIM QUE É A Artista É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.Artistas.remove(ArtistaModel);

	}

	public List<ArtistaModel> getArtistas() {
		return Artistas;
	}

	public void setArtistas(List<ArtistaModel> Artistas) {
		this.Artistas = Artistas;
	}

	public ArtistaModel getArtistaModel() {
		return ArtistaModel;
	}

	public void setArtistaModel(ArtistaModel ArtistaModel) {
		this.ArtistaModel = ArtistaModel;
	}

	/***
	 * CARREGA AS ArtistaS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {

		// RETORNAR AS ArtistaS CADASTRADAS
		this.Artistas = artistaRepository.GetArtistas();
	}

}