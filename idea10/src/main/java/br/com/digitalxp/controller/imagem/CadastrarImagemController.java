package br.com.digitalxp.controller.imagem;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.digitalxp.controller.usuario.UsuarioController;
import br.com.digitalxp.model.CategoriaImagemModel;
import br.com.digitalxp.model.ImagemModel;
import br.com.digitalxp.repository.CategoriaImagemRepository;
import br.com.digitalxp.repository.ImagemRepository;
import br.com.digitalxp.uteis.Uteis;

@Named(value = "cadastrarImagemController")
@RequestScoped
public class CadastrarImagemController {

	@Inject
	ImagemModel imagemModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	ImagemRepository ImagemRepository;

	private CategoriaImagemModel categoriaImagem;
	private List<SelectItem> listaCategoria;

	/**
	 * @return the ImagemModel
	 */
	public ImagemModel getImagemModel() {
		return imagemModel;
	}

	/**
	 * @param ImagemModel
	 *            the ImagemModel to set
	 */
	public void setImagemModel(ImagemModel ImagemModel) {
		this.imagemModel = ImagemModel;
	}

	public void setListaCategoria(List<SelectItem> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public List<SelectItem> getListaCategoria() {
		return listaCategoria;
	}

	public CategoriaImagemModel getCategoriaImagem() {
		return categoriaImagem;
	}

	public void setCategoriaImagem(CategoriaImagemModel categoriaImagem) {
		this.categoriaImagem = categoriaImagem;
	}

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void SalvarNovaImagem() {

		imagemModel.setUsuario(this.usuarioController.GetUsuarioSession());
		imagemModel.setCategoria(this.categoriaImagem);

		ImagemRepository.SalvarNovoRegistro(this.imagemModel);

		Uteis.MensagemInfo("Registro cadastrado com sucesso");

		imagemModel = null;
	}

	@PostConstruct
	public void init() {

		CategoriaImagemRepository categoriaImagemRepository = new CategoriaImagemRepository();
		this.setListaCategoria(new ArrayList<SelectItem>());
		// RETORNAR AS CategoriaImagemS CADASTRADAS
		for (CategoriaImagemModel selectItem : categoriaImagemRepository.GetCategoriaImagem()) {
			this.getListaCategoria().add(new SelectItem(selectItem.getCodigo(), selectItem.getNomeCategoriaImagem()));
		}

		categoriaImagem = new CategoriaImagemModel();
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
		// ImagemRepository.SalvarNovoRegistro(newClienteModel);
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