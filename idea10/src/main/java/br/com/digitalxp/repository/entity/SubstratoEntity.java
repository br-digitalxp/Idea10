package br.com.digitalxp.repository.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_substrato")

@NamedQueries({

		@NamedQuery(name = "SubstratoEntity.findAll", query = "SELECT p FROM SubstratoEntity p"),
		@NamedQuery(name = "SubstratoEntity.findbySubstrato", query = "SELECT p FROM SubstratoEntity p")
		
})
public class SubstratoEntity {

	@Id
	@GeneratedValue
	@Column(name = "id_substrato")
	private Integer codigo;

	@Column(name = "nm_material")
	private String material;

	@Column(name = "vl_material")
	private Double valorMaterial;

	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;

	@Column(name = "fl_primer")
	private boolean primer;

	@Column(name = "fl_coating")
	private boolean coating;

	@OneToOne
	@JoinColumn(name = "id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;

	@OneToMany(mappedBy = "substrato", targetEntity = TamanhoSubstratoEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TamanhoSubstratoEntity> listaTamanhos;

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
	public boolean isPrimer() {
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
	public boolean isCoating() {
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

	/**
	 * @return the listaTamanhos
	 */
	public List<TamanhoSubstratoEntity> getListaTamanhos() {
		return listaTamanhos;
	}

	/**
	 * @param listaTamanhos
	 *            the listaTamanhos to set
	 */
	public void setListaTamanhos(List<TamanhoSubstratoEntity> listaTamanhos) {
		this.listaTamanhos = listaTamanhos;
	}

}