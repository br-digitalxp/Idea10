//package br.com.digitalxp.controller.cliente;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.InputStream;
//import java.io.Serializable;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import org.jdom.Document;
//import org.jdom.Element;
//import org.jdom.output.XMLOutputter;
//import org.primefaces.model.DefaultStreamedContent;
//import org.primefaces.model.StreamedContent;
//
//import br.com.digitalxp.model.ClienteModel;
//import br.com.digitalxp.repository.ClienteRepository;
//
//@Named(value = "exportarRegistrosXmlController")
//@RequestScoped
//public class ExportarRegistrosXmlController implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Inject
//	transient ClienteRepository ClienteRepository;
//
//	private StreamedContent arquivoDownload;
//
//	/***
//	 * REALIZA O DOWNLOAD DO ARQUIVO XML
//	 * 
//	 * @return
//	 */
//	public StreamedContent getArquivoDownload() {
//
//		this.DownlaodArquivoXmlCliente();
//
//		return arquivoDownload;
//	}
//
//	/***
//	 * GERANDO ARQUIVO XML PARA EXPORTAÇÃO.
//	 * 
//	 * @return
//	 */
//	private File GerarXmlClientes() {
//
//		// MASCARA PARA FORMATAR A DATA QUE VAI SER ADICIONADA NO ARQUIVO XML
//		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//
//		List<ClienteModel> ClientesModel = ClienteRepository.GetClientes();
//
//		// ELEMENTO RAIZ DO NOSSO ARQUIVO XML
//		Element elementClientes = new Element("Clientes");
//
//		Document documentoClientes = new Document(elementClientes);
//
//		ClientesModel.forEach(Cliente -> {
//
//			// MONTANDO AS TAGS DO XML COM OS SEUS VALORES
//			Element elementCliente = new Element("Cliente");
//			elementCliente.addContent(new Element("codigo").setText(Cliente.getCodigo().toString()));
//			elementCliente.addContent(new Element("nome").setText(Cliente.getNome()));
//			elementCliente.addContent(new Element("cpf").setText(Cliente.getCpf()));
//
//			// FORMATANDO A DATA
//			String dataCadastroFormatada = Cliente.getDataCadastro().format(dateTimeFormatter);
//
//			elementCliente.addContent(new Element("dataCadastro").setText(dataCadastroFormatada));
//
//			elementCliente.addContent(new Element("email").setText(Cliente.getEmail()));
//			elementCliente.addContent(new Element("endereco").setText(Cliente.getEndereco()));
//			elementCliente.addContent(new Element("telefone").setText(Cliente.getTelefone()));
//			elementCliente.addContent(new Element("usuarioCadastro").setText(Cliente.getUsuarioModel().getUsuario()));
//
//			elementClientes.addContent(elementCliente);
//		});
//
//		XMLOutputter xmlGerado = new XMLOutputter();
//
//		try {
//
//			/* GERANDO O NOME DO ARQUIVO */
//			String nomeArquivo = "Clientes_".concat(java.util.UUID.randomUUID().toString()).concat(".xml");
//
//			// CAMINHO ONDE VAI SER GERADO O ARQUIVO XML
//			File arquivo = new File("C:\\Lixo\\".concat(nomeArquivo));
//
//			FileWriter fileWriter = new FileWriter(arquivo);
//
//			xmlGerado.output(documentoClientes, fileWriter);
//
//			return arquivo;
//
//		} catch (Exception ex) {
//
//			ex.printStackTrace();
//		}
//
//		return null;
//	}
//
//	/***
//	 * PREPARA O ARQUIVO PARA DOWNLOAD
//	 */
//	public void DownlaodArquivoXmlCliente() {
//
//		File arquivoXml = this.GerarXmlClientes();
//
//		InputStream inputStream;
//
//		try {
//
//			inputStream = new FileInputStream(arquivoXml.getPath());
//
//			arquivoDownload = new DefaultStreamedContent(inputStream, "application/xml", arquivoXml.getName());
//
//		} catch (FileNotFoundException e) {
//
//			e.printStackTrace();
//		}
//
//	}
//}