package br.com.digitalxp.repository.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_artistas")

@NamedQueries({

		@NamedQuery(name = "ArtistaEntity.findAll", query = "SELECT p FROM ArtistaEntity p"),
		@NamedQuery(name = "ArtistaEntity.findGettyImages", query = "SELECT p FROM ArtistaEntity p where p.nome = :nome")

})
public class ArtistaEntity {

	@Id
	@GeneratedValue
	@Column(name = "id_artista")
	private Integer codigo;

	@Column(name = "nm_artista")
	private String nome;

	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;

	@OneToOne
	@JoinColumn(name = "id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

}