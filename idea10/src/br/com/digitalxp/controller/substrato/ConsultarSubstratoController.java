package br.com.digitalxp.controller.substrato;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.model.SubstratoModel;
import br.com.digitalxp.repository.SubstratoRepository;

@Named(value = "consultarSubstratoController")
@ViewScoped
public class ConsultarSubstratoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private SubstratoModel substratoModel;

	@Produces
	private List<SubstratoModel> substratos;

	@Inject
	transient private SubstratoRepository clienteRepository;

	/***
	 * CARREGA INFORMAÇÕES DE UMA Substrato PARA SER EDITADA
	 * 
	 * @param SubstratoModel
	 */
	public void Editar(SubstratoModel substratoModel) {

		this.substratoModel = substratoModel;

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		this.clienteRepository.alterarRegistro(this.substratoModel);

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param SubstratoModel
	 */
	public void ExcluirSubstrato(SubstratoModel SubstratoModel) {

		// EXCLUI A Substrato DO BANCO DE DADOS
		this.clienteRepository.excluirRegistro(SubstratoModel.getCodigo());

		// REMOVENDO A Substrato DA LISTA
		// ASSIM QUE É A Substrato É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.substratos.remove(SubstratoModel);

	}

	public List<SubstratoModel> getSubstratos() {
		return substratos;
	}

	public void setSubstratos(List<SubstratoModel> Substratos) {
		this.substratos = Substratos;
	}

	public SubstratoModel getSubstratoModel() {
		return substratoModel;
	}

	public void setSubstratoModel(SubstratoModel SubstratoModel) {
		this.substratoModel = SubstratoModel;
	}

	/***
	 * CARREGA AS SubstratoS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {

		// RETORNAR AS SubstratoS CADASTRADAS
		this.substratos = clienteRepository.getSubstratos();
	}

}