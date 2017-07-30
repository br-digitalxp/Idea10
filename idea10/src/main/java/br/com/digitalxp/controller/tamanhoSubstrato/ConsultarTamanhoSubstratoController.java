package br.com.digitalxp.controller.tamanhoSubstrato;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.model.TamanhoSubstratoModel;
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

	public void carregarSubstrato(){
		this.tamanhoSubstratos = tamanhoSubstratoRepository.getTamanhoSubstratosBySubstrato(tamanhoSubstratoModel.getSubstrato().getCodigo());
	}
	
	/***
	 * CARREGA INFORMAÇÕES DE UMA TamanhoSubstrato PARA SER EDITADA
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
		// ASSIM QUE É A TamanhoSubstrato É REMOVIDA DA LISTA O DATATABLE É
		// ATUALIZADO
		this.tamanhoSubstratos.remove(TamanhoSubstratoModel);

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
	 * CARREGA AS TamanhoSubstratoS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {

	}

}