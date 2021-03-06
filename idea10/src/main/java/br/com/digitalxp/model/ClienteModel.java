package br.com.digitalxp.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import br.com.digitalxp.repository.entity.ClienteEntity;

public class ClienteModel {

	private BigInteger cpf;
	private String nome;
	private LocalDateTime dataCadastro;
	private String email;
	private String endereco;
	private String telefone;
	private UsuarioModel usuarioModel;

	public ClienteModel() {
	}

	public ClienteModel(ClienteEntity entity) {
		if (entity != null) {
			this.cpf = entity.getCpf();
			this.nome = entity.getNome();
			this.dataCadastro = entity.getDataCadastro();
			this.email = entity.getEmail();
			this.endereco = entity.getEndereco();
			this.telefone = entity.getTelefone();
			this.usuarioModel = new UsuarioModel(entity.getUsuarioEntity());
		}
	}

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
	 * @return the usuarioModel
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/**
	 * @param usuarioModel
	 *            the usuarioModel to set
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

}