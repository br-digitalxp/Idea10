package br.com.digitalxp.controller.internet.ordemservico;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.controller.usuario.UsuarioAdmController;
import br.com.digitalxp.model.CategoriaImagemModel;
import br.com.digitalxp.model.ClienteModel;
import br.com.digitalxp.model.ImagemModel;
import br.com.digitalxp.model.OrdemServicoModel;
import br.com.digitalxp.model.SubstratoModel;
import br.com.digitalxp.model.TamanhoSubstratoModel;
import br.com.digitalxp.model.UsuarioModel;
import br.com.digitalxp.repository.CategoriaImagemRepository;
import br.com.digitalxp.repository.ClienteRepository;
import br.com.digitalxp.repository.ImagemRepository;
import br.com.digitalxp.repository.OrdemServicoRepository;
import br.com.digitalxp.repository.SubstratoRepository;
import br.com.digitalxp.repository.TamanhoSubstratoRepository;
import br.com.digitalxp.uteis.Uteis;

@Named(value = "cadastrarOrdemServico")
@ApplicationScoped
public class CadastrarOrdemServico {

	@Inject
	TamanhoSubstratoModel tamanho;

	@Inject
	SubstratoModel substratoModel;

	@Inject
	OrdemServicoModel ordemServico;

	@Inject
	UsuarioAdmController usuarioController;

	@Inject
	SubstratoRepository substratoRepository;

	@Inject
	OrdemServicoRepository ordemServicoRepository;

	@Inject
	TamanhoSubstratoRepository tamanhoSubstratoRepository;

	private List<SelectItem> listaTamanhoSubstrato;

	private List<SelectItem> listaSubstrato;
	private ImagemGettyImage imagem;

	@Inject
	ClienteModel cliente;

	public String iniciarPagina(ImagemGettyImage imagem) {
		this.imagem = imagem;

		String retorno = "/internet/produto.xhtml";

		return retorno;
	}

	public void buscarTamanhoSubstrato() {
		List<TamanhoSubstratoModel> listaTamanho = tamanhoSubstratoRepository
				.getTamanhoSubstratosBySubstrato(this.getSubstratoModel().getCodigo());
		this.setListaTamanhoSubstrato(new ArrayList<SelectItem>());
		// RETORNAR AS CategoriaImagemS CADASTRADAS
		for (TamanhoSubstratoModel tamanho : listaTamanho) {
			this.getListaSubstrato().add(new SelectItem(tamanho.getCodigo(),
					tamanho.getValorX().toString().concat(" X ".concat(tamanho.getValorY().toString()))));
		}
	}

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void cadastraOrdemServico() {
		UsuarioModel usuario = new UsuarioModel();
		usuario.setCodigo(2);
		cliente.setUsuarioModel(usuario);
		ClienteModel clienteModel = new ClienteRepository().SalvarNovoRegistroCliente(cliente);

		ordemServico.setCliente(clienteModel);

		CategoriaImagemModel categoriaImagem = new CategoriaImagemModel(imagem.getCategoria());
		categoriaImagem.setUsuarioModel(usuario);
		categoriaImagem = new CategoriaImagemRepository().SalvarNovoRegistroCategoria(categoriaImagem);
		
		imagem.getImagem().setUsuario(usuario);
		imagem.getImagem().setCategoria(categoriaImagem);
		ImagemModel imagemModel = new ImagemRepository().SalvarNovoRegistroImagem(imagem.getImagem());
		
		
		ordemServico.setImagem(imagemModel);
		SubstratoModel substrato = new SubstratoModel();
		substrato.setCodigo(1);
		ordemServico.setSubstrato(substrato);
		tamanho.setCodigo(1);
		ordemServico.setTamanhoSubstrato(tamanho);
		ordemServico.setTamanho(1);
		ordemServico.setUsuario(usuario);

		ordemServicoRepository.SalvarNovoRegistro(ordemServico);


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

	public ImagemGettyImage getImagem() {
		return imagem;
	}

	public void setImagem(ImagemGettyImage imagem) {
		this.imagem = imagem;
	}

	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
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
