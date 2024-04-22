package br.unipar.programacaointernet.servicecep.servicecep.util.dao;

import br.unipar.programacaointernet.servicecep.servicecep.util.EntityManagerUtil;
import br.unipar.programacaointernet.servicecep.servicecep.util.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    private EntityManager entityManager = EntityManagerUtil.getManager();

    public UsuarioDAOImpl(EntityManager em) {
        this.entityManager = em;
    }


    @Override
    public void atualizar(Usuario usuario) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(usuario);
        transaction.commit();

        System.out.println("Usuário " + usuario.getNome() +
                " atualizado!");
    }

    @Override
    public void salvar(Usuario usuario) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(usuario);
        transaction.commit();

        System.out.println("Usuário " + usuario.getNome() +
                " salvo!");
    }


    @Override
    public Usuario buscarPorId(Long id) {
        return entityManager.find(Usuario.class, id);
    }

    @Override
    public void deletar(Usuario usuario) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.remove(usuario);
        transaction.commit();

        System.out.println("Usuário " + usuario.getNome() +
                " deletado!");
    }

    @Override
    public List<Usuario> buscarTodos() {
        return entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }
}
