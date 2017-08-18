package br.com.digitalxp.controller.ordemServico;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import br.com.digitalxp.mail.MailSender;
import br.com.digitalxp.model.OrdemServicoModel;
import br.com.digitalxp.repository.OrdemServicoRepository;
import br.com.digitalxp.repository.entity.OrdemServicoEntity;

@Named(value = "consultarOrdemServicoController")
@ViewScoped
public class ConsultarOrdemServicoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String ASSUNTO_EMAIL = "Novo Pedido Idea10!";

	@Inject
	transient private OrdemServicoModel ordemServicoModel;

	@Produces
	private List<OrdemServicoModel> OrdemServicos;

	@Inject
	transient private OrdemServicoRepository clienteRepository;

	/***
	 * CARREGA INFORMAÇÕES DE UMA OrdemServico PARA SER EDITADA
	 * 
	 * @param OrdemServicoModel
	 */
	public void Editar(OrdemServicoModel OrdemServicoModel) {

		this.ordemServicoModel = OrdemServicoModel;

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		this.ordemServicoModel.setDataEntrega(LocalDateTime.now().plusDays(this.ordemServicoModel.getPrazoAcordado()));
		OrdemServicoEntity entity = this.clienteRepository.AlterarRegistro(this.ordemServicoModel);

		MailSender envioEmail = new MailSender();

		try {
			envioEmail.enviarEmail(ASSUNTO_EMAIL.concat(" - ".concat(entity.getCodigo().toString())),
					montarCorpoEmail(ordemServicoModel));
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		this.ordemServicoModel = new OrdemServicoModel();

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param OrdemServicoModel
	 */
	public void ExcluirOrdemServico(OrdemServicoModel OrdemServicoModel) {

		// EXCLUI A OrdemServico DO BANCO DE DADOS
		this.clienteRepository.ExcluirRegistro(OrdemServicoModel.getCodigo());

		// REMOVENDO A OrdemServico DA LISTA
		// ASSIM QUE É A OrdemServico É REMOVIDA DA LISTA O DATATABLE É
		// ATUALIZADO
		this.OrdemServicos.remove(OrdemServicoModel);

	}

	public void consultarOrdemServico() {
		this.setOrdemServicos(new ArrayList<OrdemServicoModel>());
		this.getOrdemServicos().add(new OrdemServicoModel(
				new OrdemServicoRepository().GetOrdemServico(this.getOrdemServicoModel().getCodigo())));
	}

	private String montarCorpoEmail(OrdemServicoModel entity) {
		StringBuilder corpoEmail = new StringBuilder();

		corpoEmail.append("Olá, temos uma nova ordem de serviço cadastrada: ".concat("\n\n\n"));
		corpoEmail.append("Código da Ordem de Serviço: ".concat(entity.getCodigo().toString()).concat("\n"));
		corpoEmail.append("Substrato: ".concat(entity.getSubstrato().getMaterial()).concat("\n"));
		corpoEmail.append("Tamanho do Material: ".concat(entity.getValorX().toString()
				.concat(" X ").concat(entity.getValorY().toString())).concat("\n"));
		corpoEmail.append("Quantidade de Peças: ".concat(entity.getTamanho().toString()).concat("\n"));
		corpoEmail.append("Id da Imagem: ".concat(entity.getImagem().getCodigo().toString()).concat("\n"));
		corpoEmail.append("Número Lery Merlin: ".concat(entity.getNumeroPedidoLeroy()).concat("\n"));
		corpoEmail.append("Data de Entrega: "
				.concat(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(entity.getDataEntrega())).concat("\n"));
		corpoEmail.append("Obrigado!".concat("\n"));
		corpoEmail.append("Idea10.");

		return corpoEmail.toString();
	}

	public List<OrdemServicoModel> getOrdemServicos() {
		return OrdemServicos;
	}

	public void setOrdemServicos(List<OrdemServicoModel> OrdemServicos) {
		this.OrdemServicos = OrdemServicos;
	}

	public OrdemServicoModel getOrdemServicoModel() {
		return ordemServicoModel;
	}

	public void setOrdemServicoModel(OrdemServicoModel OrdemServicoModel) {
		this.ordemServicoModel = OrdemServicoModel;
	}

	/***
	 * CARREGA AS OrdemServicoS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {

		// RETORNAR AS OrdemServicoS CADASTRADAS
		this.OrdemServicos = clienteRepository.GetOrdemServicos();
	}

}