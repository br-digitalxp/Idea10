package br.com.digitalxp.controller.ordemServico;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.model.OrdemServicoModel;
import br.com.digitalxp.repository.OrdemServicoRepository;

@Named(value = "consultarOrdemServicoController")
@ViewScoped
public class ConsultarOrdemServicoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private OrdemServicoModel OrdemServicoModel;

	@Produces
	private List<OrdemServicoModel> OrdemServicos;

	@Inject
	transient private OrdemServicoRepository clienteRepository;

	/***
	 * CARREGA INFORMAÇÕES DE UMA OrdemServico PARA SER EDITADA
	 * 
	 * @param OrdemServicoModel
	 */
	public void Editar(OrdemServicoModel OrdemServicoModel) {

		this.OrdemServicoModel = OrdemServicoModel;

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		this.clienteRepository.AlterarRegistro(this.OrdemServicoModel);

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param OrdemServicoModel
	 */
	public void ExcluirOrdemServico(OrdemServicoModel OrdemServicoModel) {

		// EXCLUI A OrdemServico DO BANCO DE DADOS
		this.clienteRepository.ExcluirRegistro(OrdemServicoModel.getCodigo());

		// REMOVENDO A OrdemServico DA LISTA
		// ASSIM QUE É A OrdemServico É REMOVIDA DA LISTA O DATATABLE É
		// ATUALIZADO
		this.OrdemServicos.remove(OrdemServicoModel);

	}

	public List<OrdemServicoModel> getOrdemServicos() {
		return OrdemServicos;
	}

	public void setOrdemServicos(List<OrdemServicoModel> OrdemServicos) {
		this.OrdemServicos = OrdemServicos;
	}

	public OrdemServicoModel getOrdemServicoModel() {
		return OrdemServicoModel;
	}

	public void setOrdemServicoModel(OrdemServicoModel OrdemServicoModel) {
		this.OrdemServicoModel = OrdemServicoModel;
	}

	/***
	 * CARREGA AS OrdemServicoS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {

		// RETORNAR AS OrdemServicoS CADASTRADAS
		this.OrdemServicos = clienteRepository.GetOrdemServicos();
	}

}