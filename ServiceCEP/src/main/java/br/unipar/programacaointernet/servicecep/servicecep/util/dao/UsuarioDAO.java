package br.unipar.programacaointernet.servicecep.servicecep.util.dao;

import br.unipar.programacaointernet.servicecep.servicecep.util.model.Usuario;

import java.util.List;

public interface UsuarioDAO {

    public void salvar(Usuario usuario);
    public void atualizar(Usuario usuario);
    public void deletar(Usuario usuario);

    public Usuario buscarPorId(Long id);
    public List<Usuario> buscarTodos();
}
