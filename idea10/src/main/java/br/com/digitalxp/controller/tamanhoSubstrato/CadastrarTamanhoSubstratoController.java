package br.com.digitalxp.controller.tamanhoSubstrato;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.controller.usuario.UsuarioAdmController;
import br.com.digitalxp.model.SubstratoModel;
import br.com.digitalxp.model.TamanhoSubstratoModel;
import br.com.digitalxp.repository.SubstratoRepository;
import br.com.digitalxp.repository.TamanhoSubstratoRepository;
import br.com.digitalxp.uteis.Uteis;

@Named(value = "cadastrarTamanhoSubstratoController")
@RequestScoped
public class CadastrarTamanhoSubstratoController {

	@Inject
	TamanhoSubstratoModel tamanhoSubstratoModel;

	@Inject
	UsuarioAdmController usuarioController;

	@Inject
	TamanhoSubstratoRepository tamanhoSubstratoRepository;

	private SubstratoModel substrato;
	private List<SelectItem> listaSubstrato;

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void SalvarNovaTamanhoSubstrato() {

		tamanhoSubstratoModel.setUsuario(this.usuarioController.GetUsuarioSession());

		tamanhoSubstratoModel.setSubstrato(this.substrato);
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