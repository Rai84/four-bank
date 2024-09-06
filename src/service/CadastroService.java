package service;
import java.util.HashMap;
import java.util.Map;
import model.Cadastro;
import controller.CadastroRepository;

public class CadastroService {
    private CadastroRepository cadastroRepository = new CadastroRepository();
    private Map<Integer, Cadastro> cacheCadastro = new HashMap<>();

    public Cadastro buscarCadastro(int id) { // Verifica se o cliente já está em cache
        if (cacheCadastro.containsKey(id)) {
            return cacheCadastro.get(id);
        }

        // Busca o cliente no banco de dados
        Cadastro cadastro = cadastroRepository.buscarPorId(id);
        if (cadastro != null) {
            // Adiciona o cliente ao cache
            cacheCadastro.put(id, cadastro);
        }
        return cadastro;
    }

    public void salvarCadastro(Cadastro cadastro) {
        cadastroRepository.salvar(cadastro);
        // Adiciona o cliente ao cache
        int id = cacheCadastro.size() + 1; // Supondo que o ID seja incremental
        cacheCadastro.put(id, cadastro);
    }
}
