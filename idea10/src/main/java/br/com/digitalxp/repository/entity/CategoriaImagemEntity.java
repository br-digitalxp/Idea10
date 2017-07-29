package br.com.digitalxp.repository.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "tb_categoria_imagem")
@Entity
@NamedQuery(name = "CategoriaImagemEntity.findAll", query = "SELECT p FROM CategoriaImagemEntity p")
public class CategoriaImagemEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_categoria_imagem")
	private Integer codigo;

	@Column(name = "nm_categoria_imagem")
	private String nomeCategoriaImagem;

	@Column(name = "vl_ordem_menu")
	private Integer ordemMenu;

	@Column(name = "fl_categoria_principal")
	private boolean categoriaPrincipal;

	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;

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
	 * @return the nomeCategoriaImagem
	 */
	public String getNomeCategoriaImagem() {
		return nomeCategoriaImagem;
	}

	/**
	 * @param nomeCategoriaImagem
	 *            the nomeCategoriaImagem to set
	 */
	public void setNomeCategoriaImagem(String nomeCategoriaImagem) {
		this.nomeCategoriaImagem = nomeCategoriaImagem;
	}

	/**
	 * @return the ordemMenu
	 */
	public Integer getOrdemMenu() {
		return ordemMenu;
	}

	/**
	 * @param ordemMenu
	 *            the ordemMenu to set
	 */
	public void setOrdemMenu(Integer ordemMenu) {
		this.ordemMenu = ordemMenu;
	}

	/**
	 * @return the categoriaPrincipal
	 */
	public boolean isCategoriaPrincipal() {
		return categoriaPrincipal;
	}

	/**
	 * @param categoriaPrincipal
	 *            the categoriaPrincipal to set
	 */
	public void setCategoriaPrincipal(boolean categoriaPrincipal) {
		this.categoriaPrincipal = categoriaPrincipal;
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

}