package br.com.digitalxp.repository.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_imagem")

@NamedQueries({

		@NamedQuery(name = "ImagemEntity.findAll", query = "SELECT p FROM ImagemEntity p"),

})
public class ImagemEntity {

	@Id
	@Column(name = "id_imagem")
	private String codigo;

	@Column(name = "ds_caminho_imagem")
	private String caminhoImagem;

	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;

	@Column(name = "fl_exclusivo")
	private boolean exclusivo;

	@OneToOne
	@JoinColumn(name = "id_artista")
	private ArtistaEntity artista;

	@OneToOne
	@JoinColumn(name = "id_categoria_imagem")
	private CategoriaImagemEntity categoria;

	@OneToOne
	@JoinColumn(name = "id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public ArtistaEntity getArtista() {
		return artista;
	}

	public void setArtista(ArtistaEntity artista) {
		this.artista = artista;
	}

	/**
	 * @return the caminhoImagem
	 */
	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	/**
	 * @param caminhoImagem
	 *            the caminhoImagem to set
	 */
	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
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
	 * @return the exclusivo
	 */
	public boolean isExclusivo() {
		return exclusivo;
	}

	/**
	 * @param exclusivo
	 *            the exclusivo to set
	 */
	public void setExclusivo(boolean exclusivo) {
		this.exclusivo = exclusivo;
	}

	/**
	 * @return the categoria
	 */
	public CategoriaImagemEntity getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria
	 *            the categoria to set
	 */
	public void setCategoria(CategoriaImagemEntity categoria) {
		this.categoria = categoria;
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