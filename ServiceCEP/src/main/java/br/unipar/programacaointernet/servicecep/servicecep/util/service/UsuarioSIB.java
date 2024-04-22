package br.unipar.programacaointernet.servicecep.servicecep.util.service;

import br.unipar.programacaointernet.servicecep.servicecep.util.EntityManagerUtil;
import br.unipar.programacaointernet.servicecep.servicecep.util.dao.UsuarioDAO;
import br.unipar.programacaointernet.servicecep.servicecep.util.dao.UsuarioDAOImpl;
import br.unipar.programacaointernet.servicecep.servicecep.util.model.Usuario;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "br.unipar.programacaointernet.servicecep.servicecep.util.service.UsuarioSEI")
public class UsuarioSIB implements UsuarioSEI {

    @Override
    public String boasVindas(String nome) {
        return "Bem Vindo(a) " + nome + "!";
    }

    @Override
    public Usuario consultaUsuario(Long idUsuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(
                EntityManagerUtil.getManager()
        );


        return usuarioDAO.buscarPorId(idUsuario);
    }

    @Override
    public List<Usuario> consultaTodosUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(
                EntityManagerUtil.getManager()
        );

        return usuarioDAO.buscarTodos();
    }

    @Override
    public String deletaUsuario(Long idUsuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(
                EntityManagerUtil.getManager()
        );

        Usuario a = usuarioDAO.buscarPorId(idUsuario);

        usuarioDAO.deletar(a);

        return "usuário deletado com sucesso!" + a.getNome();
    }

    @Override
    public String salvarUsuario(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(
                EntityManagerUtil.getManager()
        );

        usuarioDAO.salvar(usuario);

        return "Usuário salvo com sucesso!";
    }

    @Override
    public String editarUsuario(Long idUsuario, String login, String nome, String senha) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(
                EntityManagerUtil.getManager()
        );

        Usuario a = usuarioDAO.buscarPorId(idUsuario);

        if (login != null) {
            a.setLogin(login);
        }

        if (senha != null) {
            a.setSenha(senha);
        }

        if (nome != null) {
            a.setNome(nome);
        }

        usuarioDAO.atualizar(a);

        return "Usuário editado com sucesso!";
    }


}
