package br.com.digitalxp.controller.usuario;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.model.UsuarioModel;
import br.com.digitalxp.repository.UsuarioRepository;
import br.com.digitalxp.uteis.Uteis;

@Named(value = "cadastrarUsuarioController")
@RequestScoped
public class CadastrarUsuarioController {

	@Inject
	UsuarioModel usuarioModel;

	@Inject
	UsuarioAdmController usuarioController;

	@Inject
	UsuarioRepository usuarioRepository;

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void SalvarNovaUsuario() {

		usuarioRepository.salvarNovoRegistro(this.usuarioModel);

		Uteis.MensagemInfo("Registro cadastrado com sucesso");

		usuarioModel = null;
	}

	/**
	 * @return the tamanhoSubstratoModel
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/**
	 * @param tamanhoSubstratoModel
	 *            the tamanhoSubstratoModel to set
	 */
	public void setUsuarioModel(UsuarioModel tamanhoSubstratoModel) {
		this.usuarioModel = tamanhoSubstratoModel;
	}
}