package br.com.digitalxp.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.digitalxp.model.TamanhoSubstratoModel;
import br.com.digitalxp.repository.entity.SubstratoEntity;
import br.com.digitalxp.repository.entity.TamanhoSubstratoEntity;
import br.com.digitalxp.repository.entity.UsuarioEntity;
import br.com.digitalxp.uteis.Uteis;

public class TamanhoSubstratoRepository {

	@Inject
	TamanhoSubstratoEntity tamanhoSubstratoEntity;

	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA TamanhoSubstrato
	 * 
	 * @param tamanhoSubstratoModel
	 */
	public void salvarNovoRegistro(TamanhoSubstratoModel tamanhoSubstratoModel) {

		entityManager = Uteis.JpaEntityManager();

		tamanhoSubstratoEntity = new TamanhoSubstratoEntity();
		tamanhoSubstratoEntity.setValorX(tamanhoSubstratoModel.getValorX());
		tamanhoSubstratoEntity.setValorY(tamanhoSubstratoModel.getValorY());
		tamanhoSubstratoEntity.setDataCadastro(LocalDateTime.now());

		SubstratoEntity substratoEntity = entityManager.find(SubstratoEntity.class,
				tamanhoSubstratoModel.getSubstrato().getCodigo());
		tamanhoSubstratoEntity.setSubstrato(substratoEntity);

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				tamanhoSubstratoModel.getUsuario().getCodigo());
		tamanhoSubstratoEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(tamanhoSubstratoEntity);

	}

	/***
	 * MÉTODO PARA CONSULTAR A TamanhoSubstrato
	 * 
	 * @return
	 */
	public List<TamanhoSubstratoModel> getTamanhoSubstratos() {

		List<TamanhoSubstratoModel> tamanhoSubstratosModel = new ArrayList<TamanhoSubstratoModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("TamanhoSubstratoEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<TamanhoSubstratoEntity> tamanhoSubstratosEntity = (Collection<TamanhoSubstratoEntity>) query
				.getResultList();

		for (TamanhoSubstratoEntity tamanhoSubstratoEntity : tamanhoSubstratosEntity) {
			tamanhoSubstratosModel.add(new TamanhoSubstratoModel(tamanhoSubstratoEntity));
		}

		return tamanhoSubstratosModel;

	}

	/***
	 * MÉTODO PARA CONSULTAR A TamanhoSubstrato
	 * 
	 * @return
	 */
	public List<TamanhoSubstratoModel> getTamanhoSubstratosBySubstrato(int idSubstrato) {

		List<TamanhoSubstratoModel> tamanhoSubstratosModel = new ArrayList<TamanhoSubstratoModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("TamanhoSubstratoEntity.findbySubstrato");
		query.setParameter("substrato", idSubstrato);

		@SuppressWarnings("unchecked")
		Collection<TamanhoSubstratoEntity> tamanhoSubstratosEntity = (Collection<TamanhoSubstratoEntity>) query
				.getResultList();

		for (TamanhoSubstratoEntity tamanhoSubstratoEntity : tamanhoSubstratosEntity) {
			tamanhoSubstratosModel.add(new TamanhoSubstratoModel(tamanhoSubstratoEntity));
		}

		return tamanhoSubstratosModel;

	}
	
	/***
	 * CONSULTA UMA TamanhoSubstrato CADASTRADA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private TamanhoSubstratoEntity getTamanhoSubstrato(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(TamanhoSubstratoEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param tamanhoSubstratoModel
	 */
	public void alterarRegistro(TamanhoSubstratoModel tamanhoSubstratoModel) {

		entityManager = Uteis.JpaEntityManager();

		TamanhoSubstratoEntity tamanhoSubstratoEntity = this.getTamanhoSubstrato(tamanhoSubstratoModel.getCodigo());

		tamanhoSubstratoEntity.setValorX(tamanhoSubstratoModel.getValorX());
		tamanhoSubstratoEntity.setValorY(tamanhoSubstratoModel.getValorY());
		SubstratoEntity substrato = new SubstratoRepository().getSubstrato(tamanhoSubstratoModel.getCodigo());
		tamanhoSubstratoEntity.setSubstrato(substrato);

		entityManager.merge(tamanhoSubstratoEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void excluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		TamanhoSubstratoEntity TamanhoSubstratoEntity = this.getTamanhoSubstrato(codigo);

		entityManager.remove(TamanhoSubstratoEntity);
	}

}