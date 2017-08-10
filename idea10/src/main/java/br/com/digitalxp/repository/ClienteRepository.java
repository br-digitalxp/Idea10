package br.com.digitalxp.repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.digitalxp.model.ClienteModel;
import br.com.digitalxp.model.UsuarioModel;
import br.com.digitalxp.repository.entity.ClienteEntity;
import br.com.digitalxp.repository.entity.UsuarioEntity;
import br.com.digitalxp.uteis.Uteis;

public class ClienteRepository {

	@Inject
	ClienteEntity clienteEntity;

	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA Cliente
	 * 
	 * @param ClienteModel
	 */
	public ClienteModel SalvarNovoRegistroCliente(ClienteModel ClienteModel) {

		entityManager = Uteis.JpaEntityManager();

		clienteEntity = new ClienteEntity();
		clienteEntity.setCpf(ClienteModel.getCpf());
		clienteEntity.setDataCadastro(LocalDateTime.now());
		clienteEntity.setEmail(ClienteModel.getEmail());
		clienteEntity.setEndereco(ClienteModel.getEndereco());
		clienteEntity.setNome(ClienteModel.getNome());
		clienteEntity.setTelefone(ClienteModel.getTelefone());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				ClienteModel.getUsuarioModel().getCodigo());

		clienteEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(clienteEntity);

		return new ClienteModel(clienteEntity);

	}

	public void SalvarNovoRegistro(ClienteModel ClienteModel) {
		SalvarNovoRegistroCliente(ClienteModel);
	}

	/***
	 * MÉTODO PARA CONSULTAR A Cliente
	 * 
	 * @return
	 */
	public List<ClienteModel> GetClientes() {

		List<ClienteModel> ClientesModel = new ArrayList<ClienteModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("ClienteEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<ClienteEntity> ClientesEntity = (Collection<ClienteEntity>) query.getResultList();

		ClienteModel ClienteModel = null;

		for (ClienteEntity ClienteEntity : ClientesEntity) {

			ClienteModel = new ClienteModel();
			ClienteModel.setCpf(ClienteEntity.getCpf());
			ClienteModel.setDataCadastro(ClienteEntity.getDataCadastro());
			ClienteModel.setEmail(ClienteEntity.getEmail());
			ClienteModel.setEndereco(ClienteEntity.getEndereco());
			ClienteModel.setNome(ClienteEntity.getNome());
			ClienteModel.setTelefone(ClienteEntity.getTelefone());

			UsuarioEntity usuarioEntity = ClienteEntity.getUsuarioEntity();

			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());

			ClienteModel.setUsuarioModel(usuarioModel);

			ClientesModel.add(ClienteModel);
		}

		return ClientesModel;

	}

	/***
	 * CONSULTA UMA Cliente CADASTRADA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private ClienteEntity GetCliente(BigInteger codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(ClienteEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param ClienteModel
	 */
	public void AlterarRegistro(ClienteModel ClienteModel) {

		entityManager = Uteis.JpaEntityManager();

		ClienteEntity ClienteEntity = this.GetCliente(ClienteModel.getCpf());

		ClienteEntity.setEmail(ClienteModel.getEmail());
		ClienteEntity.setCpf(ClienteModel.getCpf());
		ClienteEntity.setEndereco(ClienteModel.getEndereco());
		ClienteEntity.setNome(ClienteModel.getNome());

		entityManager.merge(ClienteEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(BigInteger codigo) {

		entityManager = Uteis.JpaEntityManager();

		ClienteEntity ClienteEntity = this.GetCliente(codigo);

		entityManager.remove(ClienteEntity);
	}

}