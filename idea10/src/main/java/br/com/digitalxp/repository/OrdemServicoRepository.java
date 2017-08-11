package br.com.digitalxp.repository;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.digitalxp.model.ClienteModel;
import br.com.digitalxp.model.ImagemModel;
import br.com.digitalxp.model.OrdemServicoModel;
import br.com.digitalxp.model.SubstratoModel;
import br.com.digitalxp.model.TamanhoSubstratoModel;
import br.com.digitalxp.model.UsuarioModel;
import br.com.digitalxp.repository.entity.ClienteEntity;
import br.com.digitalxp.repository.entity.ImagemEntity;
import br.com.digitalxp.repository.entity.OrdemServicoEntity;
import br.com.digitalxp.repository.entity.SubstratoEntity;
import br.com.digitalxp.repository.entity.TamanhoSubstratoEntity;
import br.com.digitalxp.repository.entity.UsuarioEntity;
import br.com.digitalxp.uteis.Uteis;

public class OrdemServicoRepository {

	@Inject
	OrdemServicoEntity ordemServicoEntity;

	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA OredemServico
	 * 
	 * @param ordemServicoModel
	 */
	public BigInteger SalvarNovoRegistro(OrdemServicoModel ordemServicoModel) {

		entityManager = Uteis.JpaEntityManager();
		Query q = entityManager.createNativeQuery("SELECT nextval('ordemservicoSEQ')");

		StringBuilder codigo = new StringBuilder().append(new SimpleDateFormat("yyyyMMdd").format(new Date())
				.concat(Uteis.completaZerosEsquerda(q.getSingleResult().toString())));

		ordemServicoEntity = new OrdemServicoEntity();
		ordemServicoEntity.setCodigo(new BigInteger(codigo.toString()));
		ordemServicoEntity.setTamanho(ordemServicoModel.getTamanho());
		ordemServicoEntity.setDataCadastro(LocalDateTime.now());
		ordemServicoEntity.setValorOrdemServico(ordemServicoModel.getValorOrdemServico());

		SubstratoEntity substratoEntity = entityManager.find(SubstratoEntity.class,
				ordemServicoModel.getSubstrato().getCodigo());
		ordemServicoEntity.setSubstrato(substratoEntity);

		TamanhoSubstratoEntity tamanhoSubstratoEntity = entityManager.find(TamanhoSubstratoEntity.class,
				ordemServicoModel.getTamanhoSubstrato().getCodigo());
		ordemServicoEntity.setTamanhoSubstrato(tamanhoSubstratoEntity);

		ClienteEntity clienteEntity = entityManager.find(ClienteEntity.class, ordemServicoModel.getCliente().getCpf());
		ordemServicoEntity.setCliente(clienteEntity);

		ImagemEntity imagemEntity = entityManager.find(ImagemEntity.class, ordemServicoModel.getImagem().getCodigo());
		ordemServicoEntity.setImagem(imagemEntity);

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				ordemServicoModel.getUsuario().getCodigo());
		ordemServicoEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(ordemServicoEntity);

		return ordemServicoEntity.getCodigo();

	}

	/***
	 * MÉTODO PARA CONSULTAR A OredemServico
	 * 
	 * @return
	 */
	public List<OrdemServicoModel> GetOrdemServicos() {

		List<OrdemServicoModel> OredemServicosModel = new ArrayList<OrdemServicoModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("OrdemServicoEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<OrdemServicoEntity> ordemServicosEntity = (Collection<OrdemServicoEntity>) query.getResultList();

		OrdemServicoModel ordemServicoModel = null;

		for (OrdemServicoEntity ordemServicoEntity : ordemServicosEntity) {

			ordemServicoModel = new OrdemServicoModel();
			ordemServicoModel.setCodigo(ordemServicoEntity.getCodigo());
			ordemServicoModel.setTamanho(ordemServicoEntity.getTamanho());
			ordemServicoModel.setDataCadastro(ordemServicoEntity.getDataCadastro());
			ordemServicoEntity.setValorOrdemServico(ordemServicoEntity.getValorOrdemServico());

			SubstratoModel substratoModel = new SubstratoModel(ordemServicoEntity.getSubstrato());
			ordemServicoModel.setSubstrato(substratoModel);

			TamanhoSubstratoModel tamanhoSubstratoModel = new TamanhoSubstratoModel(
					ordemServicoEntity.getTamanhoSubstrato());
			ordemServicoModel.setTamanhoSubstrato(tamanhoSubstratoModel);

			ClienteModel clienteModel = new ClienteModel(ordemServicoEntity.getCliente());
			ordemServicoModel.setCliente(clienteModel);

			ImagemModel imagemModel = new ImagemModel(ordemServicoEntity.getImagem());
			ordemServicoModel.setImagem(imagemModel);

			UsuarioEntity usuarioEntity = ordemServicoEntity.getUsuarioEntity();

			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());

			ordemServicoModel.setUsuario(usuarioModel);
			ordemServicoModel.setPrazoAcordado(ordemServicoEntity.getPrazoAcordado());
			ordemServicoModel.setDataEntrega(ordemServicoEntity.getDataEntrega());
			ordemServicoModel.setNumeroPedidoLeroy(ordemServicoEntity.getNumeroPedidoLeroy());
			OredemServicosModel.add(ordemServicoModel);
		}

		return OredemServicosModel;

	}

	/***
	 * CONSULTA UMA OredemServico CADASTRADA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	public OrdemServicoEntity GetOrdemServico(BigInteger codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(OrdemServicoEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param ordemServicoModel
	 */
	public OrdemServicoEntity AlterarRegistro(OrdemServicoModel ordemServicoModel) {

		entityManager = Uteis.JpaEntityManager();

		ordemServicoEntity = this.GetOrdemServico(ordemServicoModel.getCodigo());

		ordemServicoEntity.setTamanho(ordemServicoModel.getTamanho());
		SubstratoEntity substratoEntity = entityManager.find(SubstratoEntity.class,
				ordemServicoModel.getSubstrato().getCodigo());
		ordemServicoEntity.setSubstrato(substratoEntity);

		TamanhoSubstratoEntity tamanhoSubstratoEntity = entityManager.find(TamanhoSubstratoEntity.class,
				ordemServicoModel.getTamanhoSubstrato().getCodigo());
		ordemServicoEntity.setTamanhoSubstrato(tamanhoSubstratoEntity);

		ClienteEntity clienteEntity = entityManager.find(ClienteEntity.class, ordemServicoModel.getCliente().getCpf());
		ordemServicoEntity.setCliente(clienteEntity);

		ImagemEntity imagemEntity = entityManager.find(ImagemEntity.class, ordemServicoModel.getImagem().getCodigo());
		ordemServicoEntity.setImagem(imagemEntity);

		ordemServicoEntity.setPrazoAcordado(ordemServicoModel.getPrazoAcordado());
		ordemServicoEntity.setDataEntrega(ordemServicoModel.getDataEntrega());
		ordemServicoEntity.setNumeroPedidoLeroy(ordemServicoModel.getNumeroPedidoLeroy());
		ordemServicoEntity.setValorOrdemServico(ordemServicoModel.getValorOrdemServico());
		entityManager.merge(ordemServicoEntity);

		return ordemServicoEntity;
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(BigInteger codigo) {

		entityManager = Uteis.JpaEntityManager();

		OrdemServicoEntity OrdemServicoEntity = this.GetOrdemServico(codigo);

		entityManager.remove(OrdemServicoEntity);
	}
}