package br.com.digitalxp.controller.ordemServico;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.controller.usuario.UsuarioController;
import br.com.digitalxp.model.OrdemServicoModel;
import br.com.digitalxp.repository.OrdemServicoRepository;
import br.com.digitalxp.uteis.Uteis;

@Named(value = "cadastrarOrdemServicoController")
@RequestScoped
public class CadastrarOrdemServicoController {

	@Inject
	OrdemServicoModel ordemServicoModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	OrdemServicoRepository ordemServicoRepository;

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void SalvarNovaCliente() {

		ordemServicoModel.setUsuario(this.usuarioController.GetUsuarioSession());

		ordemServicoRepository.SalvarNovoRegistro(this.ordemServicoModel);

		Uteis.MensagemInfo("Registro cadastrado com sucesso");

		ordemServicoModel = null;
	}

	/**
	 * @return the ordemServicoModel
	 */
	public OrdemServicoModel getOrdemServicoModel() {
		return ordemServicoModel;
	}

	/**
	 * @param ordemServicoModel
	 *            the ordemServicoModel to set
	 */
	public void setOrdemServicoModel(OrdemServicoModel ordemServicoModel) {
		this.ordemServicoModel = ordemServicoModel;
	}

}