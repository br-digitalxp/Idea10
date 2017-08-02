package br.com.digitalxp.controller.usuario;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.model.UsuarioModel;
import br.com.digitalxp.repository.UsuarioRepository;

@Named(value = "consultarUsuarioController")
@ViewScoped
public class ConsultarUsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private UsuarioModel usuarioModel;

	@Produces
	private List<UsuarioModel> usuarios;

	@Inject
	transient private UsuarioRepository clienteRepository;

	/***
	 * CARREGA INFORMAÇÕES DE UMA Usuario PARA SER EDITADA
	 * 
	 * @param UsuarioModel
	 */
	public void Editar(UsuarioModel UsuarioModel) {

		this.usuarioModel = UsuarioModel;

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		this.clienteRepository.alterarRegistro(this.usuarioModel);

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param usuarioModel
	 */
	public void ExcluirUsuario(UsuarioModel usuarioModel) {

		// EXCLUI A Usuario DO BANCO DE DADOS
		this.clienteRepository.ExcluirRegistro(usuarioModel.getCodigo());

		// REMOVENDO A Usuario DA LISTA
		// ASSIM QUE É A Usuario É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.usuarios.remove(usuarioModel);

	}

	public List<UsuarioModel> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioModel> Usuarios) {
		this.usuarios = Usuarios;
	}

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel UsuarioModel) {
		this.usuarioModel = UsuarioModel;
	}

	/***
	 * CARREGA AS UsuarioS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {

		// RETORNAR AS UsuarioS CADASTRADAS
		this.usuarios = clienteRepository.GetUsuarios();
	}

}