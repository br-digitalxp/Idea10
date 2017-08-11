package br.com.digitalxp.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import br.com.digitalxp.repository.entity.OrdemServicoEntity;

public class OrdemServicoModel {

	private BigInteger codigo;
	private SubstratoModel substrato;
	private TamanhoSubstratoModel tamanhoSubstrato;
	private ClienteModel cliente;
	private Integer tamanho;
	private ImagemModel imagem;
	private LocalDateTime dataCadastro;
	private LocalDateTime dataEntrega;
	private int prazoAcordado;
	private UsuarioModel usuario;
	private String numeroPedidoLeroy;
	private Double valorOrdemServico;

	public OrdemServicoModel() {
	}

	public OrdemServicoModel(OrdemServicoEntity ordem) {
		this.codigo = ordem.getCodigo();
		this.substrato = new SubstratoModel(ordem.getSubstrato());
		this.tamanhoSubstrato = new TamanhoSubstratoModel(ordem.getTamanhoSubstrato());
		this.cliente = new ClienteModel(ordem.getCliente());
		this.tamanho = ordem.getTamanho();
		this.imagem = new ImagemModel(ordem.getImagem());
		this.dataCadastro = ordem.getDataCadastro();
		this.dataEntrega = ordem.getDataEntrega();
		this.prazoAcordado = ordem.getPrazoAcordado();
		this.usuario = new UsuarioModel(ordem.getUsuarioEntity());
		this.numeroPedidoLeroy = ordem.getNumeroPedidoLeroy();
		this.valorOrdemServico = ordem.getValorOrdemServico();
	}

	public SubstratoModel getSubstrato() {
		return substrato;
	}

	public BigInteger getCodigo() {
		return codigo;
	}

	public void setCodigo(BigInteger codigo) {
		this.codigo = codigo;
	}

	/**
	 * @param substrato
	 *            the substrato to set
	 */
	public void setSubstrato(SubstratoModel substrato) {
		this.substrato = substrato;
	}

	/**
	 * @return the tamanhoSubstrato
	 */
	public TamanhoSubstratoModel getTamanhoSubstrato() {
		return tamanhoSubstrato;
	}

	/**
	 * @param tamanhoSubstrato
	 *            the tamanhoSubstrato to set
	 */
	public void setTamanhoSubstrato(TamanhoSubstratoModel tamanhoSubstrato) {
		this.tamanhoSubstrato = tamanhoSubstrato;
	}

	/**
	 * @return the cliente
	 */
	public ClienteModel getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the tamanho
	 */
	public Integer getTamanho() {
		return tamanho;
	}

	/**
	 * @param tamanho
	 *            the tamanho to set
	 */
	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	/**
	 * @return the imagem
	 */
	public ImagemModel getImagem() {
		return imagem;
	}

	/**
	 * @param imagem
	 *            the imagem to set
	 */
	public void setImagem(ImagemModel imagem) {
		this.imagem = imagem;
	}

	/**
	 * @return the dataCadastro
	 */
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro
	 *            the dataCadastro to set
	 */
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return the usuario
	 */
	public UsuarioModel getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public String getNumeroPedidoLeroy() {
		return numeroPedidoLeroy;
	}

	public void setNumeroPedidoLeroy(String numeroPedidoLeroy) {
		this.numeroPedidoLeroy = numeroPedidoLeroy;
	}

	public LocalDateTime getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public int getPrazoAcordado() {
		return prazoAcordado;
	}

	public void setPrazoAcordado(int prazoAcordado) {
		this.prazoAcordado = prazoAcordado;
	}

	public Double getValorOrdemServico() {
		return valorOrdemServico;
	}

	public void setValorOrdemServico(Double valorOrdemServico) {
		this.valorOrdemServico = valorOrdemServico;
	}

	public boolean getDisabledBotaoAprovar() {
		if (this.getNumeroPedidoLeroy() != null && !this.getNumeroPedidoLeroy().isEmpty())
			return true;
		else
			return false;
	}

}