package br.com.digitalxp.controller.cliente;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.com.digitalxp.repository.ClienteRepository;

@Named(value = "graficoPizzaClienteController")
@RequestScoped
public class GraficoPizzaClienteController {

	@Inject
	private ClienteRepository ClienteRepository;

	private PieChartModel pieChartModel;

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	@PostConstruct
	public void init() {

		this.pieChartModel = new PieChartModel();

		this.MontaGrafico();
	}

	/***
	 * MONTA O GRÁFICO NA PÁGINA
	 */
	private void MontaGrafico() {

		// // CONSULTA OS DADOS PARA MONTAR O GRÁFICO
		// Hashtable<String, Integer> hashtableRegistros =
		// ClienteRepository.GetOrigemCliente();
		//
		// // INFORMANDO OS VALORES PARA MONTAR O GRÁFICO
		// hashtableRegistros.forEach((chave, valor) -> {
		//
		// pieChartModel.set(chave, valor);
		//
		// });
		//
		// pieChartModel.setTitle("Total de Clientes cadastrado por Tipo");
		// pieChartModel.setShowDataLabels(true);
		// pieChartModel.setLegendPosition("e");

	}
}