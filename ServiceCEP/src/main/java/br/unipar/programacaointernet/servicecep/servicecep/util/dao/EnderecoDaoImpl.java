package br.unipar.programacaointernet.servicecep.servicecep.util.dao;

import br.unipar.programacaointernet.servicecep.servicecep.util.EntityManagerUtil;
import br.unipar.programacaointernet.servicecep.servicecep.util.model.Endereco;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.util.List;

public class EnderecoDaoImpl implements EnderecoDAO {
    private EntityManager entityManager = EntityManagerUtil.getManager();
    public EnderecoDaoImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public void atualizar(Endereco endereco) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(endereco);
        transaction.commit();

        System.out.println("Endereço " + endereco.getCep() +
                " atualizado com sucesso!");
    }

    @Override
    public void deletar(Endereco endereco) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(endereco);
        transaction.commit();

        System.out.println("Endereço " + endereco.getCep() +
                " deletado!");
    }

    @Override
    public void salvar(Endereco endereco) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(endereco);
        transaction.commit();

        System.out.println("Endereço " + endereco.getCep() +
                " salvo!");
    }


    @Override
    public Endereco buscarPorId(Long id) {
        return entityManager.find(Endereco.class, id);
    }

    @Override
    public Endereco consultarCEP(String cep) {
        try {
            return entityManager.createQuery("SELECT e FROM Endereco e WHERE e.cep = :cep", Endereco.class)
                    .setParameter("cep", cep)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Endereco> buscarTodos() {
        return entityManager.createQuery("SELECT e FROM Endereco e", Endereco.class).getResultList();
    }
}
