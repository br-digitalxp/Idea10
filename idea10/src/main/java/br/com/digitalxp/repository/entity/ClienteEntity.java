package br.com.digitalxp.repository.entity;

import java.math.BigInteger;
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
@Table(name = "tb_cliente")

@NamedQueries({

		@NamedQuery(name = "ClienteEntity.findAll", query = "SELECT p FROM ClienteEntity p"),

})
public class ClienteEntity {

	@Id
	@Column(name = "id_cpf")
	private BigInteger cpf;

	@Column(name = "nm_cliente")
	private String nome;

	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;

	@Column(name = "ds_email")
	private String email;

	@Column(name = "ds_endereco")
	private String endereco;

	@Column(name = "ds_telefone")
	private String telefone;

	@OneToOne
	@JoinColumn(name = "id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;

	public BigInteger getCpf() {
		return cpf;
	}

	public void setCpf(BigInteger cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco
	 *            the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone
	 *            the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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