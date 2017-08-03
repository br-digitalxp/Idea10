package br.com.digitalxp.controller.cliente;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.model.ClienteModel;
import br.com.digitalxp.repository.ClienteRepository;

@Named(value = "consultarClienteController")
@ViewScoped
public class ConsultarClienteController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private ClienteModel ClienteModel;

	@Produces
	private List<ClienteModel> Clientes;

	@Inject
	transient private ClienteRepository clienteRepository;

	/***
	 * CARREGA INFORMAÇÕES DE UMA Cliente PARA SER EDITADA
	 * 
	 * @param ClienteModel
	 */
	public void Editar(ClienteModel ClienteModel) {

		this.ClienteModel = ClienteModel;

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		this.clienteRepository.AlterarRegistro(this.ClienteModel);

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param ClienteModel
	 */
	public void ExcluirCliente(ClienteModel ClienteModel) {

		// EXCLUI A Cliente DO BANCO DE DADOS
		this.clienteRepository.ExcluirRegistro(ClienteModel.getCodigo());

		// REMOVENDO A Cliente DA LISTA
		// ASSIM QUE É A Cliente É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.Clientes.remove(ClienteModel);

	}

	public List<ClienteModel> getClientes() {
		return Clientes;
	}

	public void setClientes(List<ClienteModel> Clientes) {
		this.Clientes = Clientes;
	}

	public ClienteModel getClienteModel() {
		return ClienteModel;
	}

	public void setClienteModel(ClienteModel ClienteModel) {
		this.ClienteModel = ClienteModel;
	}

	/***
	 * CARREGA AS ClienteS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {

		// RETORNAR AS ClienteS CADASTRADAS
		this.Clientes = clienteRepository.GetClientes();
	}

}