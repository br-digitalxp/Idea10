package br.com.digitalxp.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.digitalxp.model.UsuarioModel;
import br.com.digitalxp.repository.entity.UsuarioEntity;
import br.com.digitalxp.uteis.Uteis;

public class UsuarioRepository {

	@Inject
	UsuarioEntity usuarioEntity;

	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA Usuario
	 * 
	 * @param usuarioModel
	 */
	public void salvarNovoRegistro(UsuarioModel usuarioModel) {

		entityManager = Uteis.JpaEntityManager();

		usuarioEntity = new UsuarioEntity();
		usuarioEntity.setUsuario(usuarioModel.getUsuario());
		usuarioEntity.setSenha(usuarioModel.getSenha());
		usuarioEntity.setAtivo(usuarioModel.getAtivo());
		usuarioEntity.setDataCadastro(LocalDateTime.now());

		entityManager.persist(usuarioEntity);

	}

	/***
	 * MÉTODO PARA CONSULTAR A Usuario
	 * 
	 * @return
	 */
	public List<UsuarioModel> GetUsuarios() {

		List<UsuarioModel> UsuariosModel = new ArrayList<UsuarioModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("UsuarioEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<UsuarioEntity> usuariosEntity = (Collection<UsuarioEntity>) query.getResultList();

		for (UsuarioEntity usuarioEntity : usuariosEntity) {
			UsuariosModel.add(new UsuarioModel(usuarioEntity));
		}

		return UsuariosModel;

	}

	/***
	 * CONSULTA UMA Usuario CADASTRADA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private UsuarioEntity getUsuario(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(UsuarioEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param usuarioModel
	 */
	public void alterarRegistro(UsuarioModel usuarioModel) {

		entityManager = Uteis.JpaEntityManager();

		UsuarioEntity UsuarioEntity = this.getUsuario(usuarioModel.getCodigo());

		usuarioEntity.setUsuario(usuarioModel.getUsuario());
		usuarioEntity.setSenha(usuarioModel.getSenha());
		usuarioEntity.setAtivo(usuarioModel.getAtivo());

		entityManager.merge(UsuarioEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		UsuarioEntity UsuarioEntity = this.getUsuario(codigo);

		entityManager.remove(UsuarioEntity);
	}

}