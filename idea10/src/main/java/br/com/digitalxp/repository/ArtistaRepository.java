package br.com.digitalxp.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.digitalxp.model.ArtistaModel;
import br.com.digitalxp.model.UsuarioModel;
import br.com.digitalxp.repository.entity.ArtistaEntity;
import br.com.digitalxp.repository.entity.UsuarioEntity;
import br.com.digitalxp.uteis.Uteis;

public class ArtistaRepository {

	@Inject
	ArtistaEntity artistaEntity;

	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA Artista
	 * 
	 * @param ArtistaModel
	 */
	public ArtistaModel SalvarNovoRegistroArtista(ArtistaModel ArtistaModel) {

		entityManager = Uteis.JpaEntityManager();

		artistaEntity = new ArtistaEntity();
		artistaEntity.setDataCadastro(LocalDateTime.now());
		artistaEntity.setNome(ArtistaModel.getNome());
		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				ArtistaModel.getUsuarioModel().getCodigo());

		artistaEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(artistaEntity);

		return new ArtistaModel(artistaEntity);

	}

	public void SalvarNovoRegistro(ArtistaModel ArtistaModel) {
		SalvarNovoRegistroArtista(ArtistaModel);
	}

	/***
	 * MÉTODO PARA CONSULTAR A Artista
	 * 
	 * @return
	 */
	public List<ArtistaModel> GetArtistas() {

		List<ArtistaModel> ArtistasModel = new ArrayList<ArtistaModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("ArtistaEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<ArtistaEntity> ArtistasEntity = (Collection<ArtistaEntity>) query.getResultList();

		ArtistaModel ArtistaModel = null;

		for (ArtistaEntity ArtistaEntity : ArtistasEntity) {

			ArtistaModel = new ArtistaModel();
			ArtistaModel.setCodigo(ArtistaEntity.getCodigo());
			ArtistaModel.setDataCadastro(ArtistaEntity.getDataCadastro());
			ArtistaModel.setNome(ArtistaEntity.getNome());
			UsuarioEntity usuarioEntity = ArtistaEntity.getUsuarioEntity();

			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());

			ArtistaModel.setUsuarioModel(usuarioModel);

			ArtistasModel.add(ArtistaModel);
		}

		return ArtistasModel;

	}

	/***
	 * CONSULTA UMA Artista CADASTRADA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private ArtistaEntity GetArtista(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(ArtistaEntity.class, codigo);
	}

	public ArtistaModel getArtista(int codigo) {

		return new ArtistaModel(GetArtista(codigo));
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param artistaModel
	 */
	public void AlterarRegistro(ArtistaModel artistaModel) {

		entityManager = Uteis.JpaEntityManager();

		ArtistaEntity ArtistaEntity = this.GetArtista(artistaModel.getCodigo());

		ArtistaEntity.setNome(artistaModel.getNome());

		entityManager.merge(ArtistaEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		ArtistaEntity ArtistaEntity = this.GetArtista(codigo);

		entityManager.remove(ArtistaEntity);
	}

}