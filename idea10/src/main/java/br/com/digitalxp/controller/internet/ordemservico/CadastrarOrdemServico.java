package br.com.digitalxp.controller.internet.ordemservico;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
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

	private List<TamanhoSubstratoModel> listaTamanhoSubstrato;

	private List<SubstratoModel> listaSubstrato;
	private ImagemGettyImage imagem;
	
	private Double valor;

	@Inject
	ClienteModel cliente;

	public String iniciarPagina(ImagemGettyImage imagem) {
		this.imagem = imagem;
		this.listaSubstrato = substratoRepository.getSubstratos();
		if(this.getListaSubstrato() != null && !this.getListaSubstrato().isEmpty()){
			this.substratoModel = this.listaSubstrato.get(0);
			this.listaTamanhoSubstrato = tamanhoSubstratoRepository.getTamanhoSubstratosBySubstrato(this.substratoModel.getCodigo());
			this.tamanho = this.listaTamanhoSubstrato.get(0);
		}
		String retorno = "/internet/produto.xhtml";

		return retorno;
	}
	
	public void consultarTamanhoSubstrato() {
		this.listaTamanhoSubstrato = tamanhoSubstratoRepository.getTamanhoSubstratosBySubstrato(this.substratoModel.getCodigo());
		this.tamanho = this.listaTamanhoSubstrato.get(0);
	}


	/*public void buscarTamanhoSubstrato() {
		this.setListaTamanhoSubstrato(new ArrayList<SelectItem>());		
		for (TamanhoSubstratoModel tamanhoSubstrato : tamanhoSubstratoRepository.getTamanhoSubstratosBySubstrato(substratoModel.getCodigo())) {
			this.getListaTamanhoSubstrato().add(new SelectItem(tamanhoSubstrato.getCodigo(), tamanhoSubstrato.getValorX().toString().concat(" X ".concat(tamanhoSubstrato.getValorY().toString()))));
		}

		tamanho = new TamanhoSubstratoModel();
	}
*/
	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void cadastraOrdemServico() {
		UsuarioModel usuario = new UsuarioModel();
		usuario.setCodigo(1);
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

	public List<TamanhoSubstratoModel> getListaTamanhoSubstrato() {
		return listaTamanhoSubstrato;
	}

	public void setListaTamanhoSubstrato(List<TamanhoSubstratoModel> listaTamanhoSubstrato) {
		this.listaTamanhoSubstrato = listaTamanhoSubstrato;
	}

	public List<SubstratoModel> getListaSubstrato() {
		return listaSubstrato;
	}

	public void setListaSubstrato(List<SubstratoModel> listaSubstrato) {
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
	
	public void calcularValor(){
		Double valorM2 = substratoModel.getValorMaterial();
		int area = tamanho.getValorX() * tamanho.getValorY();
		valor = valorM2*area;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@PostConstruct
	public void init() {

	}

}
