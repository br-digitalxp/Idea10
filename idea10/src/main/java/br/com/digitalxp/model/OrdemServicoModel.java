package br.com.digitalxp.model;

import java.time.LocalDateTime;

public class OrdemServicoModel {

	private Integer codigo;
	private SubstratoModel substrato;
	private TamanhoSubstratoModel tamanhoSubstrato;
	private ClienteModel cliente;
	private Integer tamanho;
	private ImagemModel imagem;
	private LocalDateTime dataCadastro;
	private UsuarioModel usuario;
	
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
	 * @return the tamanhoSubstrato
	 */
	public TamanhoSubstratoModel getTamanhoSubstrato() {
		return tamanhoSubstrato;
	}

	/**
	 * @param tamanhoSubstrato
	 *            the tamanhoSubstrato to set
	 */
	public void setTamanhoSubstrato(TamanhoSubstratoModel tamanhoSubstrato) {
		this.tamanhoSubstrato = tamanhoSubstrato;
	}

	/**
	 * @return the cliente
	 */
	public ClienteModel getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
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
	public ImagemModel getImagem() {
		return imagem;
	}

	/**
	 * @param imagem
	 *            the imagem to set
	 */
	public void setImagem(ImagemModel imagem) {
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