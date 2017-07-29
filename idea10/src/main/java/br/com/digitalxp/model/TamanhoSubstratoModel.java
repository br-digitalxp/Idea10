package br.com.digitalxp.model;

import java.time.LocalDateTime;

import br.com.digitalxp.repository.entity.TamanhoSubstratoEntity;

public class TamanhoSubstratoModel {

	private Integer codigo;
	private Integer valorX;
	private Integer valorY;
	private LocalDateTime dataCadastro;
	private SubstratoModel substrato;
	private UsuarioModel usuario;

	public TamanhoSubstratoModel(TamanhoSubstratoEntity entity) {
		this.codigo = entity.getCodigo();
		this.valorX = entity.getValorX();
		this.valorY = entity.getValorY();
		this.dataCadastro = entity.getDataCadastro();
		this.substrato = new SubstratoModel(entity.getSubstrato());
		this.usuario = new UsuarioModel(entity.getUsuarioEntity());
	}

	public TamanhoSubstratoModel() {
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the valorX
	 */
	public Integer getValorX() {
		return valorX;
	}

	/**
	 * @param valorX
	 *            the valorX to set
	 */
	public void setValorX(Integer valorX) {
		this.valorX = valorX;
	}

	/**
	 * @return the valorY
	 */
	public Integer getValorY() {
		return valorY;
	}

	/**
	 * @param valorY
	 *            the valorY to set
	 */
	public void setValorY(Integer valorY) {
		this.valorY = valorY;
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
	 * @return the substrato
	 */
	public SubstratoModel getSubstrato() {
		return substrato;
	}

	/**
	 * @param substrato
	 *            the substrato to set
	 */
	public void setSubstrato(SubstratoModel substrato) {
		this.substrato = substrato;
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

}