package service;
import java.util.HashMap;
import java.util.Map;

import model.Cliente;
import controller.ClienteRepository;

public class ClienteService {
    private ClienteRepository clienteRepository = new ClienteRepository();
    private Map<Integer, Cliente> cacheClientes = new HashMap<>();

    public Cliente buscarCliente(int id) { // Verifica se o cliente já está em cache
        
        if (cacheClientes.containsKey(id)) {
            return cacheClientes.get(id);
        }

        // Busca o cliente no banco de dados
        Cliente cliente = clienteRepository.buscarPorId(id);
        if (cliente != null) {
            // Adiciona o cliente ao cache
            cacheClientes.put(id, cliente);
        }
        return cliente;
    }

}
