package br.com.digitalxp.controller.tamanhoSubstrato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.model.SubstratoModel;
import br.com.digitalxp.model.TamanhoSubstratoModel;
import br.com.digitalxp.repository.SubstratoRepository;
import br.com.digitalxp.repository.TamanhoSubstratoRepository;

@Named(value = "consultarTamanhoSubstratoController")
@ViewScoped
public class ConsultarTamanhoSubstratoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private TamanhoSubstratoModel tamanhoSubstratoModel;

	@Produces
	private List<TamanhoSubstratoModel> tamanhoSubstratos;

	@Inject
	transient private TamanhoSubstratoRepository tamanhoSubstratoRepository;

	private SubstratoModel substrato;
	private List<SelectItem> listaSubstrato;

	public void carregarTamanhoSubstrato() {
		this.tamanhoSubstratoModel.setSubstrato(this.getSubstrato());
		this.tamanhoSubstratos = tamanhoSubstratoRepository
				.getTamanhoSubstratosBySubstrato(tamanhoSubstratoModel.getSubstrato().getCodigo());
	}

	/***
	 * CARREGA INFORMA��ES DE UMA TamanhoSubstrato PARA SER EDITADA
	 * 
	 * @param TamanhoSubstratoModel
	 */
	public void editar(TamanhoSubstratoModel TamanhoSubstratoModel) {

		this.tamanhoSubstratoModel = TamanhoSubstratoModel;

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void alterarRegistro() {

		this.tamanhoSubstratoModel.setSubstrato(this.getSubstrato());
		this.tamanhoSubstratoRepository.alterarRegistro(this.tamanhoSubstratoModel);

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param TamanhoSubstratoModel
	 */
	public void excluirTamanhoSubstrato(TamanhoSubstratoModel TamanhoSubstratoModel) {

		// EXCLUI A TamanhoSubstrato DO BANCO DE DADOS
		this.tamanhoSubstratoRepository.excluirRegistro(TamanhoSubstratoModel.getCodigo());

		// REMOVENDO A TamanhoSubstrato DA LISTA
		// ASSIM QUE � A TamanhoSubstrato � REMOVIDA DA LISTA O DATATABLE �
		// ATUALIZADO
		this.tamanhoSubstratos.remove(TamanhoSubstratoModel);

	}

	public SubstratoModel getSubstrato() {
		return substrato;
	}

	public void setSubstrato(SubstratoModel substrato) {
		this.substrato = substrato;
	}

	public List<SelectItem> getListaSubstrato() {
		return listaSubstrato;
	}

	public void setListaSubstrato(List<SelectItem> listaSubstrato) {
		this.listaSubstrato = listaSubstrato;
	}

	public List<TamanhoSubstratoModel> getTamanhoSubstratos() {
		return tamanhoSubstratos;
	}

	public void setTamanhoSubstratos(List<TamanhoSubstratoModel> TamanhoSubstratos) {
		this.tamanhoSubstratos = TamanhoSubstratos;
	}

	public TamanhoSubstratoModel getTamanhoSubstratoModel() {
		return tamanhoSubstratoModel;
	}

	public void setTamanhoSubstratoModel(TamanhoSubstratoModel TamanhoSubstratoModel) {
		this.tamanhoSubstratoModel = TamanhoSubstratoModel;
	}

	/***
	 * CARREGA AS TamanhoSubstratoS NA INICIALIZA��O
	 */
	@PostConstruct
	public void init() {
		SubstratoRepository substratoRepository = new SubstratoRepository();
		this.setListaSubstrato(new ArrayList<SelectItem>());
		// RETORNAR AS CategoriaImagemS CADASTRADAS
		for (SubstratoModel selectItem : substratoRepository.getSubstratos()) {
			this.getListaSubstrato().add(new SelectItem(selectItem.getCodigo(), selectItem.getMaterial()));
		}

		substrato = new SubstratoModel();
	}

}