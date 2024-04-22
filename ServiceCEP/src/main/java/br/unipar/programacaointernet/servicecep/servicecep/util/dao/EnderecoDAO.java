package br.unipar.programacaointernet.servicecep.servicecep.util.dao;

import br.unipar.programacaointernet.servicecep.servicecep.util.model.Endereco;

import java.util.List;

public interface EnderecoDAO {

    public void salvar(Endereco endereco);
    public void atualizar(Endereco endereco);
    public void deletar(Endereco endereco);

    public Endereco buscarPorId(Long id);

    public Endereco consultarCEP(String cep);
    public List<Endereco> buscarTodos();
}
