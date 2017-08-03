package br.com.digitalxp.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.digitalxp.model.ImagemModel;
import br.com.digitalxp.repository.entity.CategoriaImagemEntity;
import br.com.digitalxp.repository.entity.ImagemEntity;
import br.com.digitalxp.repository.entity.UsuarioEntity;
import br.com.digitalxp.uteis.Uteis;

public class ImagemRepository {

	@Inject
	ImagemEntity imagemEntity;

	EntityManager entityManager;

	/***
	 * M�TODO RESPONS�VEL POR SALVAR UMA NOVA Cliente
	 * 
	 * @param imagemModel
	 */
	public void SalvarNovoRegistro(ImagemModel imagemModel) {

		entityManager = Uteis.JpaEntityManager();

		imagemEntity = new ImagemEntity();
		imagemEntity.setAutor(0);
		imagemEntity.setCaminhoImagem(imagemModel.getCaminhoImagem());
		imagemEntity.setExclusivo(imagemModel.getExclusivo());
		imagemEntity.setDataCadastro(LocalDateTime.now());

		CategoriaImagemEntity categoria = entityManager.find(CategoriaImagemEntity.class,
				imagemModel.getCategoria().getCodigo());
		imagemEntity.setCategoria(categoria);

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, imagemModel.getUsuario().getCodigo());
		imagemEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(imagemEntity);

	}

	/***
	 * M�TODO PARA CONSULTAR A Cliente
	 * 
	 * @return
	 */
	public List<ImagemModel> GetImagem() {

		List<ImagemModel> categoriasImagemModel = new ArrayList<ImagemModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("ImagemEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<ImagemEntity> imagnsEntity = (Collection<ImagemEntity>) query.getResultList();

		for (ImagemEntity imagemEntity : imagnsEntity) {
			categoriasImagemModel.add(new ImagemModel(imagemEntity));
		}

		return categoriasImagemModel;

	}

	/***
	 * CONSULTA UMA Categoria CADASTRADA PELO C�DIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private ImagemEntity getImagem(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(ImagemEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param imagemModel
	 */
	public void AlterarRegistro(ImagemModel imagemModel) {

		entityManager = Uteis.JpaEntityManager();

		ImagemEntity imagemEntity = this.getImagem(imagemModel.getCodigo());

		imagemEntity.setAutor(imagemModel.getAutor());
		imagemEntity.setCaminhoImagem(imagemModel.getCaminhoImagem());
		imagemEntity
				.setCategoria(entityManager.find(CategoriaImagemEntity.class, imagemModel.getCategoria().getCodigo()));
		imagemEntity.setExclusivo(imagemModel.getExclusivo());

		entityManager.merge(imagemEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		ImagemEntity ImagemEntity = this.getImagem(codigo);

		entityManager.remove(ImagemEntity);
	}

}