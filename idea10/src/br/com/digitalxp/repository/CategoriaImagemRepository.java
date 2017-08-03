package br.com.digitalxp.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.digitalxp.model.CategoriaImagemModel;
import br.com.digitalxp.repository.entity.CategoriaImagemEntity;
import br.com.digitalxp.repository.entity.UsuarioEntity;
import br.com.digitalxp.uteis.Uteis;

public class CategoriaImagemRepository {

	@Inject
	CategoriaImagemEntity categoriaImagemEntity;

	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA Cliente
	 * 
	 * @param categoriaImagemModel
	 */
	public void SalvarNovoRegistro(CategoriaImagemModel categoriaImagemModel) {

		entityManager = Uteis.JpaEntityManager();

		categoriaImagemEntity = new CategoriaImagemEntity();
		categoriaImagemEntity.setNomeCategoriaImagem(categoriaImagemModel.getNomeCategoriaImagem());
		categoriaImagemEntity.setOrdemMenu(categoriaImagemModel.getOrdemMenu());
		categoriaImagemEntity.setCategoriaPrincipal(categoriaImagemModel.isCategoriaPrincipal());
		categoriaImagemEntity.setDataCadastro(LocalDateTime.now());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				categoriaImagemModel.getUsuarioModel().getCodigo());

		categoriaImagemEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(categoriaImagemEntity);

	}

	/***
	 * MÉTODO PARA CONSULTAR A Cliente
	 * 
	 * @return
	 */
	public List<CategoriaImagemModel> GetCategoriaImagem() {

		List<CategoriaImagemModel> categoriasImagemModel = new ArrayList<CategoriaImagemModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("CategoriaImagemEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<CategoriaImagemEntity> categoriasImagemEntity = (Collection<CategoriaImagemEntity>) query
				.getResultList();

		for (CategoriaImagemEntity categoriaImagemEntity : categoriasImagemEntity) {

			categoriasImagemModel.add(new CategoriaImagemModel(categoriaImagemEntity));
		}

		return categoriasImagemModel;

	}

	/***
	 * CONSULTA UMA Categoria CADASTRADA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private CategoriaImagemEntity GetCategoriaImagem(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(CategoriaImagemEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param categoriaImagemModel
	 */
	public void AlterarRegistro(CategoriaImagemModel categoriaImagemModel) {

		entityManager = Uteis.JpaEntityManager();

		CategoriaImagemEntity categoriaImagemEntity = this.GetCategoriaImagem(categoriaImagemModel.getCodigo());

		categoriaImagemEntity.setNomeCategoriaImagem(categoriaImagemModel.getNomeCategoriaImagem());
		categoriaImagemEntity.setOrdemMenu(categoriaImagemModel.getOrdemMenu());
		categoriaImagemEntity.setCategoriaPrincipal(categoriaImagemModel.isCategoriaPrincipal());

		entityManager.merge(categoriaImagemEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		CategoriaImagemEntity categoriaImagemEntity = this.GetCategoriaImagem(codigo);

		entityManager.remove(categoriaImagemEntity);
	}

}