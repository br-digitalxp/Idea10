package br.com.digitalxp.controller.artista;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.controller.usuario.UsuarioAdmController;
import br.com.digitalxp.model.ArtistaModel;
import br.com.digitalxp.repository.ArtistaRepository;
import br.com.digitalxp.uteis.Uteis;

@Named(value = "cadastrarArtistaController")
@RequestScoped
public class CadastrarArtistaController {

	@Inject
	ArtistaModel artistaModel;

	@Inject
	UsuarioAdmController usuarioController;

	@Inject
	ArtistaRepository artistaRepository;

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void SalvarNovaArtista() {

		artistaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());

		artistaRepository.SalvarNovoRegistro(this.artistaModel);

		Uteis.MensagemInfo("Registro cadastrado com sucesso");

		artistaModel = null;
	}

	/**
	 * @return the artistaModel
	 */
	public ArtistaModel getArtistaModel() {
		return artistaModel;
	}

	/**
	 * @param artistaModel
	 *            the artistaModel to set
	 */
	public void setArtistaModel(ArtistaModel artistaModel) {
		this.artistaModel = artistaModel;
	}

}