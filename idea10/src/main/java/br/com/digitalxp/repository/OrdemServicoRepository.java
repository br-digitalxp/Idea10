package br.com.digitalxp.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
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
	public void SalvarNovoRegistro(OrdemServicoModel ordemServicoModel) {

		entityManager = Uteis.JpaEntityManager();

		ordemServicoEntity = new OrdemServicoEntity();
		ordemServicoEntity.setTamanho(ordemServicoModel.getTamanho());
		ordemServicoEntity.setDataCadastro(LocalDateTime.now());

		SubstratoEntity substratoEntity = entityManager.find(SubstratoEntity.class, ordemServicoModel.getSubstrato().getCodigo());
		ordemServicoEntity.setSubstrato(substratoEntity);

		TamanhoSubstratoEntity tamanhoSubstratoEntity = entityManager.find(TamanhoSubstratoEntity.class, ordemServicoModel.getTamanhoSubstrato().getCodigo());
		ordemServicoEntity.setTamanhoSubstrato(tamanhoSubstratoEntity);

		ClienteEntity clienteEntity = entityManager.find(ClienteEntity.class, ordemServicoModel.getCliente().getCodigo());
		ordemServicoEntity.setCliente(clienteEntity);

		ImagemEntity imagemEntity = entityManager.find(ImagemEntity.class, ordemServicoModel.getImagem().getCodigo());
		ordemServicoEntity.setImagem(imagemEntity);

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, ordemServicoModel.getUsuario().getCodigo());
		ordemServicoEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(ordemServicoEntity);

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
		Collection<OrdemServicoEntity> OredemServicosEntity = (Collection<OrdemServicoEntity>) query.getResultList();

		OrdemServicoModel OrdemServicoModel = null;

		for (OrdemServicoEntity OrdemServicoEntity : OredemServicosEntity) {

			OrdemServicoModel = new OrdemServicoModel();
			OrdemServicoModel.setCodigo(OrdemServicoEntity.getCodigo());
			OrdemServicoModel.setTamanho(OrdemServicoEntity.getTamanho());
			OrdemServicoModel.setDataCadastro(OrdemServicoEntity.getDataCadastro());

			SubstratoModel substratoModel = new SubstratoModel(OrdemServicoEntity.getSubstrato());
			OrdemServicoModel.setSubstrato(substratoModel);

			TamanhoSubstratoModel tamanhoSubstratoModel = new TamanhoSubstratoModel(OrdemServicoEntity.getTamanhoSubstrato());
			OrdemServicoModel.setTamanhoSubstrato(tamanhoSubstratoModel);

			ClienteModel clienteModel = new ClienteModel(OrdemServicoEntity.getCliente());
			OrdemServicoModel.setCliente(clienteModel);
			
			ImagemModel imagemModel = new ImagemModel(OrdemServicoEntity.getImagem());
			OrdemServicoModel.setImagem(imagemModel);
			
			UsuarioEntity usuarioEntity = OrdemServicoEntity.getUsuarioEntity();

			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());

			OrdemServicoModel.setUsuario(usuarioModel);

			OredemServicosModel.add(OrdemServicoModel);
		}

		return OredemServicosModel;

	}

	/***
	 * CONSULTA UMA OredemServico CADASTRADA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private OrdemServicoEntity GetOrdemServico(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(OrdemServicoEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param ordemServicoModel
	 */
	public void AlterarRegistro(OrdemServicoModel ordemServicoModel) {

		entityManager = Uteis.JpaEntityManager();

		OrdemServicoEntity OrdemServicoEntity = this.GetOrdemServico(ordemServicoModel.getCodigo());
		
		ordemServicoEntity.setTamanho(ordemServicoModel.getTamanho());
		SubstratoEntity substratoEntity = entityManager.find(SubstratoEntity.class, ordemServicoModel.getSubstrato().getCodigo());
		ordemServicoEntity.setSubstrato(substratoEntity);

		TamanhoSubstratoEntity tamanhoSubstratoEntity = entityManager.find(TamanhoSubstratoEntity.class, ordemServicoModel.getTamanhoSubstrato().getCodigo());
		ordemServicoEntity.setTamanhoSubstrato(tamanhoSubstratoEntity);

		ClienteEntity clienteEntity = entityManager.find(ClienteEntity.class, ordemServicoModel.getCliente().getCodigo());
		ordemServicoEntity.setCliente(clienteEntity);

		ImagemEntity imagemEntity = entityManager.find(ImagemEntity.class, ordemServicoModel.getImagem().getCodigo());
		ordemServicoEntity.setImagem(imagemEntity);

		entityManager.merge(OrdemServicoEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		OrdemServicoEntity OrdemServicoEntity = this.GetOrdemServico(codigo);

		entityManager.remove(OrdemServicoEntity);
	}

}