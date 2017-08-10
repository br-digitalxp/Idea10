package br.com.digitalxp.controller.usuario;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.digitalxp.model.UsuarioModel;
import br.com.digitalxp.repository.LoginRepository;
import br.com.digitalxp.repository.entity.UsuarioEntity;
import br.com.digitalxp.uteis.Uteis;

@Named(value = "usuarioAdmController")
@SessionScoped
public class UsuarioAdmController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioModel usuarioModel;

	@Inject
	private LoginRepository loginRepository;

	@Inject
	private UsuarioEntity usuarioEntity;

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public UsuarioModel GetUsuarioSession() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		return (UsuarioModel) facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}

	public String Logout() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/internet/index.xhtml";
	}

	public String EfetuarLogin() {

		if (StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())) {

			Uteis.Mensagem("Favor informar o login!");
			return null;
		} else if (StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())) {

			Uteis.Mensagem("Favor informara senha!");
			return null;
		} else {

			usuarioEntity = loginRepository.ValidaUsuario(usuarioModel);

			if (usuarioEntity != null) {

				usuarioModel.setSenha(null);
				usuarioModel.setCodigo(usuarioEntity.getCodigo());

				FacesContext facesContext = FacesContext.getCurrentInstance();

				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);

				return "/sistema/home";
			} else {

				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}

	}

}