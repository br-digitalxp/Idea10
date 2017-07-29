package br.com.digitalxp.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.digitalxp.model.SubstratoModel;
import br.com.digitalxp.repository.entity.SubstratoEntity;
import br.com.digitalxp.repository.entity.UsuarioEntity;
import br.com.digitalxp.uteis.Uteis;

public class SubstratoRepository {

	@Inject
	SubstratoEntity substratoEntity;

	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA Substrato
	 * 
	 * @param substratoModel
	 */
	public void salvarNovoRegistro(SubstratoModel substratoModel) {

		entityManager = Uteis.JpaEntityManager();

		substratoEntity = new SubstratoEntity();
		substratoEntity.setMaterial(substratoModel.getMaterial());
		substratoEntity.setValorMaterial(substratoModel.getValorMaterial());
		substratoEntity.setDataCadastro(LocalDateTime.now());
		substratoEntity.setPrimer(substratoModel.getPrimer());
		substratoEntity.setCoating(substratoModel.getCoating());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, substratoModel.getUsuario().getCodigo());

		substratoEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(substratoEntity);

	}

	/***
	 * MÉTODO PARA CONSULTAR A Substrato
	 * 
	 * @return
	 */
	public List<SubstratoModel> getSubstratos() {

		List<SubstratoModel> SubstratosModel = new ArrayList<SubstratoModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("SubstratoEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<SubstratoEntity> substratosEntity = (Collection<SubstratoEntity>) query.getResultList();

		for (SubstratoEntity substratoEntity : substratosEntity) {
			SubstratosModel.add(new SubstratoModel(substratoEntity));
		}

		return SubstratosModel;

	}

	/***
	 * CONSULTA UMA Substrato CADASTRADA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	public SubstratoEntity getSubstrato(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(SubstratoEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param substratoModel
	 */
	public void alterarRegistro(SubstratoModel substratoModel) {

		entityManager = Uteis.JpaEntityManager();

		SubstratoEntity substratoEntity = this.getSubstrato(substratoModel.getCodigo());

		substratoEntity.setMaterial(substratoModel.getMaterial());
		substratoEntity.setValorMaterial(substratoModel.getValorMaterial());
		substratoEntity.setPrimer(substratoModel.getPrimer());
		substratoEntity.setCoating(substratoModel.getCoating());

		entityManager.merge(substratoEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void excluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		SubstratoEntity SubstratoEntity = this.getSubstrato(codigo);

		entityManager.remove(SubstratoEntity);
	}

}