package br.com.digitalxp.controller.substrato;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.controller.usuario.UsuarioController;
import br.com.digitalxp.model.SubstratoModel;
import br.com.digitalxp.model.TamanhoSubstratoModel;
import br.com.digitalxp.repository.SubstratoRepository;
import br.com.digitalxp.repository.TamanhoSubstratoRepository;
import br.com.digitalxp.uteis.Uteis;

@Named(value = "cadastrarSubstratoController")
@RequestScoped
public class CadastrarSubstratoController {

	private TamanhoSubstratoModel tamanho = new TamanhoSubstratoModel();;

	@Inject
	SubstratoModel substratoModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	SubstratoRepository substratoRepository;

	@Inject
	TamanhoSubstratoRepository tamanhoSubstratoRepository;

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void SalvarNovaSubstrato() {

		substratoModel.setUsuario(this.usuarioController.GetUsuarioSession());

		substratoRepository.salvarNovoRegistro(this.substratoModel);

		// for (TamanhoSubstratoModel tamanho :
		// substratoModel.getListaTamanhos()) {
		// tamanhoSubstratoRepository.salvarNovoRegistro(tamanho);
		// }

		Uteis.MensagemInfo("Registro cadastrado com sucesso");

		substratoModel = null;
		tamanho = new TamanhoSubstratoModel();
	}

	public void adicionarTamanhoSubstrato() {
		this.substratoModel.getListaTamanhos().add(tamanho);
	}

	public void editarTamanhoSubstrato(TamanhoSubstratoModel tamanho) {
		this.tamanho = tamanho;
		this.substratoModel.getListaTamanhos().remove(tamanho);
	}

	public void excluirTamanhoSubstrato(TamanhoSubstratoModel tamanho) {
		this.substratoModel.getListaTamanhos().remove(tamanho);
	}

	public SubstratoModel getSubstratoModel() {
		return substratoModel;
	}

	public void setSubstratoModel(SubstratoModel clienteModel) {
		this.substratoModel = clienteModel;
	}

	/**
	 * @return the tamanho
	 */
	public TamanhoSubstratoModel getTamanho() {
		return tamanho;
	}

	/**
	 * @param tamanho
	 *            the tamanho to set
	 */
	public void setTamanho(TamanhoSubstratoModel tamanho) {
		this.tamanho = tamanho;
	}

}