package br.com.digitalxp.controller.internet.ordemservico;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
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
import br.com.digitalxp.repository.entity.ImagemEntity;
import br.com.digitalxp.repository.entity.SubstratoEntity;
import br.com.digitalxp.repository.entity.TamanhoSubstratoEntity;
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
	private Long numeroPedido;
	private Double valor;
	private Integer quantidade;
	private static final Double conversor = 0.0001;

	@Inject
	ClienteModel cliente;

	public String iniciarPagina(ImagemGettyImage imagem) {
		this.imagem = imagem;
		this.setListaSubstrato(new ArrayList<SelectItem>());
		// RETORNAR AS CategoriaImagemS CADASTRADAS
		List<SubstratoModel> listSubstrato = substratoRepository.getSubstratos();
		for (SubstratoModel selectItem : listSubstrato) {
			this.getListaSubstrato().add(new SelectItem(selectItem.getCodigo(), selectItem.getMaterial()));
		}
		substratoModel = new SubstratoModel();
		//substratoModel.setCodigo(listSubstrato.get(0).getCodigo());
		//consultarTamanhoSubstrato(this.getListaSubstrato().get(0));		
		String retorno = "/internet/produto.xhtml";
		tamanho = null;
		resetQuantidade();
		return retorno;
	}

	public void buscarTamanhoSubstrato() {
		this.setListaTamanhoSubstrato(new ArrayList<SelectItem>());		
		for (TamanhoSubstratoModel tamanhoSubstrato : tamanhoSubstratoRepository.getTamanhoSubstratosBySubstrato(substratoModel.getCodigo())) {
			this.getListaTamanhoSubstrato().add(new SelectItem(tamanhoSubstrato.getCodigo(), tamanhoSubstrato.getValorX().toString().concat("cm X ".concat(tamanhoSubstrato.getValorY().toString().concat(" cm")))));
		}

		tamanho = new TamanhoSubstratoModel();
		
		//tamanho.setCodigo((int)this.getListaTamanhoSubstrato().get(0).getValue());
		//calcularValor();
		resetQuantidade();
	}
	
	public void resetQuantidade(){
		quantidade =null;
	}

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public String cadastraOrdemServico() {
		UsuarioModel usuario = new UsuarioModel();
		usuario.setCodigo(1);
		cliente.setUsuarioModel(usuario);

		// ImagemModel ImagemModel imagemModel = null;
		ClienteRepository cr = new ClienteRepository();
		ClienteModel clienteModel = cr.getClienteByCPF(cliente.getCpf());
		if (clienteModel == null || clienteModel.getCpf() == null
				|| clienteModel.getCpf().equals(new BigInteger("0"))) {
			clienteModel = cr.SalvarNovoRegistroCliente(cliente);
		}

		ordemServico.setCliente(clienteModel);

		CategoriaImagemModel categoriaImagem = new CategoriaImagemModel(imagem.getCategoria());
		categoriaImagem.setUsuarioModel(usuario);
		categoriaImagem = new CategoriaImagemRepository().SalvarNovoRegistroCategoria(categoriaImagem);

		imagem.getImagem().setUsuario(usuario);
		imagem.getImagem().setCategoria(categoriaImagem);
		
		//ImagemModel ImagemModel imagemModel = null;
		ImagemRepository ir = new ImagemRepository();
		ImagemModel imagemModel = ir.getImagemById(imagem.getImagem().getCodigo());
		if (imagemModel == null || imagemModel.getCodigo() == null || imagemModel.getCodigo().isEmpty()) {
			imagemModel = ir.SalvarNovoRegistroImagem(imagem.getImagem());
		}

		ordemServico.setImagem(imagemModel);
		ordemServico.setSubstrato(substratoModel);
		ordemServico.setTamanhoSubstrato(tamanho);
		ordemServico.setTamanho(1);
		ordemServico.setUsuario(usuario);

		BigInteger numeroPedido = ordemServicoRepository.SalvarNovoRegistro(ordemServico);

		Uteis.MensagemInfo("Pedido Nº" + numeroPedido + " realizado com sucesso");

		return "sucesso";
		/*
		 * substratoModel = null; tamanho = new TamanhoSubstratoModel();
		 */
	}

	public void consultarTamanhoSubstrato(SelectItem item) {
		this.setListaTamanhoSubstrato(new ArrayList<SelectItem>());
		// RETORNAR AS CategoriaImagemS CADASTRADAS
		for (TamanhoSubstratoModel tamanhoSubstrato : tamanhoSubstratoRepository.getTamanhoSubstratosBySubstrato((Integer) item.getValue())) {
			this.getListaTamanhoSubstrato().add(new SelectItem(tamanhoSubstrato.getCodigo(), tamanhoSubstrato.getValorX().toString().concat(" CM X ".concat(tamanhoSubstrato.getValorY().toString().concat(" CM")))));
		}

		tamanho = new TamanhoSubstratoModel();
		tamanho.setCodigo((int) this.getListaTamanhoSubstrato().get(0).getValue());
		calcularValor();
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

	public void calcularValor() {
		SubstratoEntity st = substratoRepository.getSubstrato(substratoModel.getCodigo());
		Double valorM2 = st.getValorMaterial();
		TamanhoSubstratoEntity et = tamanhoSubstratoRepository.getTamanhoSubstrato(tamanho.getCodigo());
		int area = et.getValorX() * et.getValorY();

		valor = valorM2 * area * conversor * quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@PostConstruct
	public void init() {

	}

}
