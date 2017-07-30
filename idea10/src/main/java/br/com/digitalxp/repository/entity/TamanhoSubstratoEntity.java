package br.com.digitalxp.repository.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tamanho_substrato")

@NamedQueries({

		@NamedQuery(name = "TamanhoSubstratoEntity.findAll", query = "SELECT p FROM TamanhoSubstratoEntity p"),
		@NamedQuery(name = "TamanhoSubstratoEntity.findbySubstrato", query = "SELECT p FROM TamanhoSubstratoEntity p where p.substrato = :substrato") 
		
})
public class TamanhoSubstratoEntity {

	@Id
	@GeneratedValue
	@Column(name = "id_tamanho_substrato")
	private Integer codigo;

	@Column(name = "vl_x")
	private Integer valorX;

	@Column(name = "vl_y")
	private Integer valorY;

	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;

	@ManyToOne
	@JoinColumn(name = "id_substrato")
	private SubstratoEntity substrato;

	@OneToOne
	@JoinColumn(name = "id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;

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
	public SubstratoEntity getSubstrato() {
		return substrato;
	}

	/**
	 * @param substrato
	 *            the substrato to set
	 */
	public void setSubstrato(SubstratoEntity substrato) {
		this.substrato = substrato;
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

}