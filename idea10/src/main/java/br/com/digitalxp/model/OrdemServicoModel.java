package br.com.digitalxp.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import br.com.digitalxp.repository.entity.OrdemServicoEntity;

public class OrdemServicoModel {

	private BigInteger codigo;
	private SubstratoModel substrato;
	private Integer valorX;
	private Integer valorY;
	private Integer tamanho;
	private ImagemModel imagem;
	private LocalDateTime dataCadastro;
	private LocalDateTime dataEntrega;
	private int prazoAcordado;
	private UsuarioModel usuario;
	private String numeroPedidoLeroy;
	private Double valorOrdemServico;
	private Boolean flagCmyk;
	private Boolean flagFundoBranco;
	private Boolean flagVernizLocalizado;

	public OrdemServicoModel() {
	}

	public OrdemServicoModel(OrdemServicoEntity ordem) {
		this.codigo = ordem.getCodigo();
		this.substrato = new SubstratoModel(ordem.getSubstrato());
		this.tamanho = ordem.getTamanho();
		this.valorX = ordem.getValorX();
		this.valorY = ordem.getValorY();
		this.imagem = new ImagemModel(ordem.getImagem());
		this.dataCadastro = ordem.getDataCadastro();
		this.dataEntrega = ordem.getDataEntrega();
		this.prazoAcordado = ordem.getPrazoAcordado();
		this.usuario = new UsuarioModel(ordem.getUsuarioEntity());
		this.numeroPedidoLeroy = ordem.getNumeroPedidoLeroy();
		this.valorOrdemServico = ordem.getValorOrdemServico();
		this.flagCmyk = ordem.getFlagCmyk();
		this.flagFundoBranco = ordem.getFlagFundoBranco();
		this.flagVernizLocalizado = ordem.getFlagVernizLocalizado();
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

	public Boolean getFlagCmyk() {
		return flagCmyk;
	}

	public void setFlagCmyk(Boolean flagCmyk) {
		this.flagCmyk = flagCmyk;
	}

	public Boolean getFlagFundoBranco() {
		return flagFundoBranco;
	}

	public void setFlagFundoBranco(Boolean flagFundoBranco) {
		this.flagFundoBranco = flagFundoBranco;
	}

	public Boolean getFlagVernizLocalizado() {
		return flagVernizLocalizado;
	}

	public void setFlagVernizLocalizado(Boolean flagVernizLocalizado) {
		this.flagVernizLocalizado = flagVernizLocalizado;
	}

	public Boolean getDisabledBotaoAprovar() {
		if (this.getNumeroPedidoLeroy() != null && !this.getNumeroPedidoLeroy().isEmpty())
			return true;
		else
			return false;
	}

}