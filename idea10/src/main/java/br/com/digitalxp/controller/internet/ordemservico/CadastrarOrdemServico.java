package br.com.digitalxp.controller.internet.ordemservico;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
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
import br.com.digitalxp.repository.entity.SubstratoEntity;
import br.com.digitalxp.uteis.Uteis;

/**
 * @author bruno.bmoraes
 *
 */
@Named(value = "cadastrarOrdemServico")
@ApplicationScoped
public class CadastrarOrdemServico {

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
	private Integer valorX;
	private Integer valorY;
	private String valor;
	private Integer quantidade;
	private static final Double conversor = 0.0001;
	private Double valorFinal;
	private boolean renderizaPasso2 = false;
	private boolean renderizaPasso3 = false;

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
		String retorno = "/internet/produto.xhtml";
		resetQuantidade();
		renderizaPasso2 = false;
		renderizaPasso3 = false;
		return retorno;
	}

	public void buscarTamanhoSubstrato() {
		this.setRenderizaPasso2(true);
	}

	public void resetQuantidade() {
		quantidade = null;
	}

	public void renderizaPasso3() {
		this.resetQuantidade();
		if (this.getValorX() != null && this.getValorY() != null)
			renderizaPasso3 = true;
	}

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public String cadastraOrdemServico() {
		UsuarioModel usuario = new UsuarioModel();
		usuario.setCodigo(1);
		cliente.setUsuarioModel(usuario);

		// ImagemModel ImagemModel imagemModel = null;
		if (cliente.getCpf() != null && cliente.getCpf().longValue() != 0) {
			ClienteRepository cr = new ClienteRepository();
			ClienteModel clienteModel = cr.getClienteByCPF(cliente.getCpf());
			if (clienteModel == null || clienteModel.getCpf() == null
					|| clienteModel.getCpf().equals(new BigInteger("0"))) {
				clienteModel = cr.SalvarNovoRegistroCliente(cliente);
			}
		}

		CategoriaImagemModel categoriaImagem = new CategoriaImagemModel(imagem.getCategoria());
		categoriaImagem.setUsuarioModel(usuario);
		categoriaImagem = new CategoriaImagemRepository().SalvarNovoRegistroCategoria(categoriaImagem);

		imagem.getImagem().setUsuario(usuario);
		imagem.getImagem().setCategoria(categoriaImagem);

		// ImagemModel ImagemModel imagemModel = null;
		ImagemRepository ir = new ImagemRepository();
		ImagemModel imagemModel = ir.getImagemById(imagem.getImagem().getCodigo());
		if (imagemModel == null || imagemModel.getCodigo() == null || imagemModel.getCodigo().isEmpty()) {
			imagemModel = ir.SalvarNovoRegistroImagem(imagem.getImagem());
		}

		ordemServico.setImagem(imagemModel);
		ordemServico.setSubstrato(substratoModel);
		ordemServico.setValorX(this.valorX);
		ordemServico.setValorY(this.valorY);
		ordemServico.setTamanho(1);
		ordemServico.setUsuario(usuario);
		ordemServico.setValorOrdemServico(valorFinal);

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
		for (TamanhoSubstratoModel tamanhoSubstrato : tamanhoSubstratoRepository
				.getTamanhoSubstratosBySubstrato((Integer) item.getValue())) {
			this.getListaTamanhoSubstrato()
					.add(new SelectItem(tamanhoSubstrato.getCodigo(), tamanhoSubstrato.getValorX().toString()
							.concat(" CM X ".concat(tamanhoSubstrato.getValorY().toString().concat(" CM")))));
		}

		calcularValor();
	}

	public SubstratoModel getSubstratoModel() {
		return substratoModel;
	}

	public void setSubstratoModel(SubstratoModel substratoModel) {
		this.substratoModel = substratoModel;
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
		int area = this.getValorX() * this.getValorY();
		Double areaM2 = area * conversor;
		Double valorParcial = valorM2 * areaM2 * quantidade;
		valorFinal = valorParcial;
		if (this.getOrdemServico().getFlagCmyk())
			valorFinal = valorParcial * 0.3 + valorFinal;
		if (this.getOrdemServico().getFlagFundoBranco())
			valorFinal = valorParcial * 0.3 + valorFinal;
		if (this.getOrdemServico().getFlagVernizLocalizado())
			valorFinal = valorParcial * 0.3 + valorFinal;
		Locale ptBr = new Locale("pt", "BR");
		valor = NumberFormat.getCurrencyInstance(ptBr).format(valorFinal);
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
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

	public Integer getValorX() {
		return valorX;
	}

	public void setValorX(Integer valorX) {
		this.valorX = valorX;
	}

	public Integer getValorY() {
		return valorY;
	}

	public void setValorY(Integer valorY) {
		this.valorY = valorY;
	}

	public boolean getRenderizaPasso2() {
		return renderizaPasso2;
	}

	public void setRenderizaPasso2(boolean renderizaPasso2) {
		this.renderizaPasso2 = renderizaPasso2;
	}

	public boolean getRenderizaPasso3() {
		return renderizaPasso3;
	}

	public void setRenderizaPasso3(boolean renderizaPasso3) {
		this.renderizaPasso3 = renderizaPasso3;
	}

	public OrdemServicoModel getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServicoModel ordemServico) {
		this.ordemServico = ordemServico;
	}

	@PostConstruct
	public void init() {

	}

}
