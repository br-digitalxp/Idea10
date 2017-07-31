package br.com.digitalxp.controller.categoriaImagem;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.digitalxp.controller.usuario.UsuarioAdmController;
import br.com.digitalxp.model.CategoriaImagemModel;
import br.com.digitalxp.repository.CategoriaImagemRepository;
import br.com.digitalxp.uteis.Uteis;

@Named(value = "cadastrarCategoriaImagemController")
@RequestScoped
public class CadastrarCategoriaImagemController {

	@Inject
	CategoriaImagemModel categoriaImagemModel;

	@Inject
	UsuarioAdmController usuarioController;

	@Inject
	CategoriaImagemRepository categoriaImagemRepository;

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/**
	 * @return the categoriaImagemModel
	 */
	public CategoriaImagemModel getCategoriaImagemModel() {
		return categoriaImagemModel;
	}

	/**
	 * @param categoriaImagemModel
	 *            the categoriaImagemModel to set
	 */
	public void setCategoriaImagemModel(CategoriaImagemModel categoriaImagemModel) {
		this.categoriaImagemModel = categoriaImagemModel;
	}

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void SalvarNovaCategoriaImagem() {

		categoriaImagemModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());

		categoriaImagemRepository.SalvarNovoRegistro(this.categoriaImagemModel);

		Uteis.MensagemInfo("Registro cadastrado com sucesso");

		categoriaImagemModel = null;
	}

	/**
	 * REALIZANDO UPLOAD DE ARQUIVO
	 */
	public void UploadRegistros() {

		// DocumentBuilderFactory factory =
		// DocumentBuilderFactory.newInstance();
		//
		// try {
		//
		// if (this.file.getFileName().equals("")) {
		// Uteis.MensagemAtencao("Nenhum arquivo selecionado!");
		// return;
		// }
		//
		// DocumentBuilder builder = factory.newDocumentBuilder();
		//
		// Document doc = builder.parse(this.file.getInputstream());
		//
		// Element element = doc.getDocumentElement();
		//
		// NodeList nodes = element.getChildNodes();
		//
		// for (int i = 0; i < nodes.getLength(); i++) {
		//
		// Node node = nodes.item(i);
		//
		// if (node.getNodeType() == Node.ELEMENT_NODE) {
		//
		// Element elementCliente = (Element) node;
		//
		// // PEGANDO OS VALORES DO ARQUIVO XML
		// String nome =
		// elementCliente.getElementsByTagName("nome").item(0).getChildNodes().item(0).getNodeValue();
		// String email =
		// elementCliente.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
		// String cpf =
		// elementCliente.getElementsByTagName("cpf").item(0).getChildNodes().item(0).getNodeValue();
		// String telefone =
		// elementCliente.getElementsByTagName("telefone").item(0).getChildNodes().item(0).getNodeValue();
		// String endereco =
		// elementCliente.getElementsByTagName("endereco").item(0).getChildNodes().item(0).getNodeValue();
		//
		// ClienteModel newClienteModel = new ClienteModel();
		//
		// newClienteModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
		// newClienteModel.setCpf(cpf);
		// newClienteModel.setEmail(email);
		// newClienteModel.setEndereco(endereco);
		// newClienteModel.setNome(nome);
		// newClienteModel.setTelefone(telefone);
		//
		// // SALVANDO UM REGISTRO QUE VEIO DO ARQUIVO XML
		// categoriaImagemRepository.SalvarNovoRegistro(newClienteModel);
		// }
		// }
		//
		// Uteis.MensagemInfo("Registros cadastrados com sucesso!");
		//
		// } catch (ParserConfigurationException e) {
		//
		// e.printStackTrace();
		//
		// } catch (SAXException e) {
		//
		// e.printStackTrace();
		//
		// } catch (IOException e) {
		//
		// e.printStackTrace();
		// }

	}

}