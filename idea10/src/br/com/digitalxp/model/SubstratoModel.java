package br.com.digitalxp.model;

import java.time.LocalDateTime;
import java.util.List;

import br.com.digitalxp.repository.entity.SubstratoEntity;

public class SubstratoModel {

	private Integer codigo;
	private String material;
	private Double valorMaterial;
	private LocalDateTime dataCadastro;
	private boolean primer;
	private boolean coating;
	private UsuarioModel usuario;
	private List<TamanhoSubstratoModel> listaTamanhos;

	public SubstratoModel(SubstratoEntity entity) {
		this.codigo = entity.getCodigo();
		this.material = entity.getMaterial();
		this.valorMaterial = entity.getValorMaterial();
		this.dataCadastro = entity.getDataCadastro();
		this.primer = entity.isPrimer();
		this.coating = entity.isCoating();
		this.usuario = new UsuarioModel(entity.getUsuarioEntity());
	}

	public SubstratoModel() {
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
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @param material
	 *            the material to set
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * @return the valorMaterial
	 */
	public Double getValorMaterial() {
		return valorMaterial;
	}

	/**
	 * @param valorMaterial
	 *            the valorMaterial to set
	 */
	public void setValorMaterial(Double valorMaterial) {
		this.valorMaterial = valorMaterial;
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
	 * @return the primer
	 */
	public boolean getPrimer() {
		return primer;
	}

	/**
	 * @param primer
	 *            the primer to set
	 */
	public void setPrimer(boolean primer) {
		this.primer = primer;
	}

	/**
	 * @return the coating
	 */
	public boolean getCoating() {
		return coating;
	}

	/**
	 * @param coating
	 *            the coating to set
	 */
	public void setCoating(boolean coating) {
		this.coating = coating;
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

	/**
	 * @return the listaTamanhos
	 */
	public List<TamanhoSubstratoModel> getListaTamanhos() {
		return listaTamanhos;
	}

	/**
	 * @param listaTamanhos
	 *            the listaTamanhos to set
	 */
	public void setListaTamanhos(List<TamanhoSubstratoModel> listaTamanhos) {
		this.listaTamanhos = listaTamanhos;
	}

}