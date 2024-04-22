package br.unipar.programacaointernet.servicecep.servicecep.util.service;

import br.unipar.programacaointernet.servicecep.servicecep.util.EntityManagerUtil;
import br.unipar.programacaointernet.servicecep.servicecep.util.dao.EnderecoDAO;
import br.unipar.programacaointernet.servicecep.servicecep.util.dao.EnderecoDaoImpl;
import br.unipar.programacaointernet.servicecep.servicecep.util.model.Endereco;
import jakarta.jws.WebService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@WebService(endpointInterface = "br.unipar.programacaointernet.servicecep.servicecep.util.service.EnderecoSEI")
public class EnderecoSIB implements EnderecoSEI {

    @Override
    public Endereco consultaEndereco(String cep) {
        try {
            EnderecoDAO enderecoDao = new EnderecoDaoImpl(
                    EntityManagerUtil.getManager()
            );

            Endereco endereco = enderecoDao.consultarCEP(cep);

            if (endereco == null) {
                enderecoDao.salvar(getViaCep(cep));

                return enderecoDao.consultarCEP(cep);
            } else {
                return endereco;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Endereco> consultaTodosEndereco() {
        EnderecoDAO enderecoDao = new EnderecoDaoImpl(
                EntityManagerUtil.getManager()
        );

        return enderecoDao.buscarTodos();
    }

    @Override
    public String deletaEndereco(Long idEndereco) {
        EnderecoDAO enderecoDao = new EnderecoDaoImpl(
                EntityManagerUtil.getManager()
        );

        Endereco endereco = enderecoDao.buscarPorId(idEndereco);

        enderecoDao.deletar(endereco);

        return "Endereço deletado com sucesso!";
    }

    @Override
    public String salvarEndereco(String cep) throws Exception {
        EnderecoDAO enderecoDao = new EnderecoDaoImpl(
                EntityManagerUtil.getManager()
        );

        enderecoDao.salvar(getViaCep(cep));

        return "Endereço salvo com sucesso!";
    }

    @Override
    public String editarEndereco(Long idEndereco, String bairro, String localidade) {
        EnderecoDAO enderecoDao = new EnderecoDaoImpl(
                EntityManagerUtil.getManager()
        );

        Endereco endereco = enderecoDao.buscarPorId(idEndereco);

        if (bairro != null) {
            endereco.setBairro(bairro);
        }

        if (localidade != null) {
            endereco.setLocalidade(localidade);
        }

        enderecoDao.atualizar(endereco);

        return "Endereço editado com sucesso!";
    }


    private static Endereco getViaCep(String cep) throws Exception{
        URL url = new URL("http://viacep.com.br/ws/"
                +cep.replace("-", "")
                .replace(".", "")
                +"/xml/");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        String result = "";
        while ((inputLine = in.readLine()) != null)
            result += inputLine;

        in.close();
        //  return result;
        Endereco objEndereco;
        objEndereco = Endereco.unmarshalFromString(result);

        return objEndereco;
    }
}
