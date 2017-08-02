package br.com.digitalxp.controller.internet.ordemservico;

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

@Named(value = "cadastrarOrdemServico")
@RequestScoped
public class CadastrarOrdemServico {

	@Inject
	TamanhoSubstratoModel tamanho;

	@Inject
	SubstratoModel substratoModel;

	@Inject
	UsuarioAdmController usuarioController;

	@Inject
	SubstratoRepository substratoRepository;

	@Inject
	TamanhoSubstratoRepository tamanhoSubstratoRepository;

	private List<SelectItem> listaTamanhoSubstrato;

	private List<SelectItem> listaSubstrato;

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void cadastraOrdemServico() {

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

	public void consultarTamanhoSubstrato(SelectItem item) {
		this.setListaTamanhoSubstrato(new ArrayList<SelectItem>());
		// RETORNAR AS CategoriaImagemS CADASTRADAS
		for (TamanhoSubstratoModel tamanhoSubstrato : tamanhoSubstratoRepository
				.getTamanhoSubstratosBySubstrato((Integer) item.getValue())) {
			this.getListaTamanhoSubstrato().add(new SelectItem(tamanhoSubstrato.getCodigo(), tamanhoSubstrato
					.getValorX().toString().concat(" X ".concat(tamanhoSubstrato.getValorY().toString()))));
		}

		tamanho = new TamanhoSubstratoModel();
	}

	public SubstratoModel getSubstratoModel() {
		return substratoModel;
	}

	public void setSubstratoModel(SubstratoModel substratoModel) {
		this.substratoModel = substratoModel;
	}

	public TamanhoSubstratoModel getTamanho() {
		return tamanho;
	}

	public void setTamanho(TamanhoSubstratoModel tamanho) {
		this.tamanho = tamanho;
	}

	public List<SelectItem> getListaTamanhoSubstrato() {
		return listaTamanhoSubstrato;
	}

	public void setListaTamanhoSubstrato(List<SelectItem> listaTamanhoSubstrato) {
		this.listaTamanhoSubstrato = listaTamanhoSubstrato;
	}

	public List<SelectItem> getListaSubstrato() {
		return listaSubstrato;
	}

	public void setListaSubstrato(List<SelectItem> listaSubstrato) {
		this.listaSubstrato = listaSubstrato;
	}

	@PostConstruct
	public void init() {
		this.setListaSubstrato(new ArrayList<SelectItem>());
		// RETORNAR AS CategoriaImagemS CADASTRADAS
		for (SubstratoModel selectItem : substratoRepository.getSubstratos()) {
			this.getListaSubstrato().add(new SelectItem(selectItem.getCodigo(), selectItem.getMaterial()));
		}

		substratoModel = new SubstratoModel();
	}

}
