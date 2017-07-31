package br.com.digitalxp.controller.cliente;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.controller.usuario.UsuarioAdmController;
import br.com.digitalxp.model.ClienteModel;
import br.com.digitalxp.repository.ClienteRepository;
import br.com.digitalxp.uteis.Uteis;

@Named(value = "cadastrarClienteController")
@RequestScoped
public class CadastrarClienteController {

	@Inject
	ClienteModel clienteModel;

	@Inject
	UsuarioAdmController usuarioController;

	@Inject
	ClienteRepository clienteRepository;

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void SalvarNovaCliente() {

		clienteModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());

		clienteRepository.SalvarNovoRegistro(this.clienteModel);

		Uteis.MensagemInfo("Registro cadastrado com sucesso");

		clienteModel = null;
	}

	/**
	 * @return the clienteModel
	 */
	public ClienteModel getClienteModel() {
		return clienteModel;
	}

	/**
	 * @param clienteModel
	 *            the clienteModel to set
	 */
	public void setClienteModel(ClienteModel clienteModel) {
		this.clienteModel = clienteModel;
	}

}