package br.com.digitalxp.repository.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_ordem_servico")

@NamedQueries({

		@NamedQuery(name = "OrdemServicoEntity.findAll", query = "SELECT p FROM OrdemServicoEntity p"),
		@NamedQuery(name = "OrdemServicoEntity.findByDate", query = "SELECT p FROM OrdemServicoEntity p where p.dataCadastro between :inicio and :fim and p.numeroPedidoLeroy is null"),

})
public class OrdemServicoEntity {

	@Id
	@Column(name = "id_ordem_servico")
	private BigInteger codigo;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_substato")
	private SubstratoEntity substrato;

	@Column(name = "vl_tamanho_x")
	private Integer valorX;

	@Column(name = "vl_tamanho_y")
	private Integer valorY;

	@Column(name = "nm_tamanho")
	private Integer tamanho;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_imagem")
	private ImagemEntity imagem;

	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;

	@Column(name = "dt_entrega")
	private LocalDateTime dataEntrega;

	@Column(name = "nu_prazo_acordado")
	private int prazoAcordado;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;

	@Column(name = "nm_pedido_leroy")
	private String numeroPedidoLeroy;

	@Column(name = "vl_ordem_servico")
	private Double valorOrdemServico;

	@Column(name = "fl_cmyk")
	private Boolean flagCmyk;

	@Column(name = "fl_fundo_branco")
	private Boolean flagFundoBranco;

	@Column(name = "fl_verniz_localizado")
	private Boolean flagVernizLocalizado;
	
	/**
	 * @return the codigo
	 */

	/**
	 * @return the substrato
	 */
	public SubstratoEntity getSubstrato() {
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
	public void setSubstrato(SubstratoEntity substrato) {
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
	public ImagemEntity getImagem() {
		return imagem;
	}

	/**
	 * @param imagem
	 *            the imagem to set
	 */
	public void setImagem(ImagemEntity imagem) {
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
	 * @return the usuarioEntity
	 */
	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	/**
	 * @param usuarioEntity
	 *            the usuarioEntity to set
	 */
	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
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

	public String getNumeroPedidoLeroy() {
		return numeroPedidoLeroy;
	}

	public void setNumeroPedidoLeroy(String numeroPedidoLeroy) {
		this.numeroPedidoLeroy = numeroPedidoLeroy;
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

}