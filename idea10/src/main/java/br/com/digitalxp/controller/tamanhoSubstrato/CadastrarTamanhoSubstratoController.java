package br.com.digitalxp.controller.tamanhoSubstrato;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.controller.usuario.UsuarioController;
import br.com.digitalxp.model.TamanhoSubstratoModel;
import br.com.digitalxp.repository.TamanhoSubstratoRepository;
import br.com.digitalxp.uteis.Uteis;

@Named(value = "cadastrarTamanhoSubstratoController")
@RequestScoped
public class CadastrarTamanhoSubstratoController {

	@Inject
	TamanhoSubstratoModel tamanhoSubstratoModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	TamanhoSubstratoRepository tamanhoSubstratoRepository;

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void SalvarNovaTamanhoSubstrato() {

		tamanhoSubstratoModel.setUsuario(this.usuarioController.GetUsuarioSession());

		tamanhoSubstratoRepository.salvarNovoRegistro(this.tamanhoSubstratoModel);

		Uteis.MensagemInfo("Registro cadastrado com sucesso");

		tamanhoSubstratoModel = null;
	}

	/**
	 * @return the tamanhoSubstratoModel
	 */
	public TamanhoSubstratoModel getTamanhoSubstratoModel() {
		return tamanhoSubstratoModel;
	}

	/**
	 * @param tamanhoSubstratoModel
	 *            the tamanhoSubstratoModel to set
	 */
	public void setTamanhoSubstratoModel(TamanhoSubstratoModel tamanhoSubstratoModel) {
		this.tamanhoSubstratoModel = tamanhoSubstratoModel;
	}

}