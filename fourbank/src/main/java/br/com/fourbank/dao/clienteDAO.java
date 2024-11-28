package br.com.fourbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fourbank.model.Cliente;

public class ClienteDAO {

    // Método para criar um cliente no banco de dados
    public boolean criarCliente(Cliente cliente) {
        String SQL = "INSERT INTO CLIENTE (NOME, CPF, ENDERECO, TELEFONE, EMAIL, DATA_NASCIMENTO, SENHA) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCpf());
            preparedStatement.setString(3, cliente.getEndereco());
            preparedStatement.setString(4, cliente.getTelefone());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setString(6, cliente.getDataNascimento());
            preparedStatement.setString(7, cliente.getSenha());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cliente.setId(generatedKeys.getInt(1)); // Atribuindo ID gerado ao cliente
                    }
                }
                return true; // Cliente criado com sucesso
            }

        } catch (SQLException e) {
            System.out.println("Erro ao criar cliente: " + e.getMessage());
        }
        return false; // Falha na criação do cliente
    }

    // Método para verificar se o CPF já está cadastrado
    public boolean verificarCpfExistente(String cpf) {
        String SQL = "SELECT COUNT(*) FROM CLIENTE WHERE CPF = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, cpf);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; // CPF existe se o contador for maior que 0
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar CPF existente: " + e.getMessage());
        }
        return false; // CPF não encontrado ou erro na execução
    }

    // Método para obter um cliente por ID
    public Cliente obterClientePorId(int clienteId) {
        String SQL = "SELECT * FROM CLIENTE WHERE CLIENTE_ID = ?";
        Cliente cliente = null;

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, clienteId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cliente = new Cliente();
                    cliente.setId(resultSet.getInt("CLIENTE_ID"));
                    cliente.setNome(resultSet.getString("NOME"));
                    cliente.setEndereco(resultSet.getString("ENDERECO"));
                    cliente.setTelefone(resultSet.getString("TELEFONE"));
                    cliente.setEmail(resultSet.getString("EMAIL"));
                    cliente.setDataNascimento(resultSet.getString("DATA_NASCIMENTO"));
                    cliente.setCpf(resultSet.getString("CPF")); // Incluído CPF apenas para referência
                    cliente.setSenha(resultSet.getString("SENHA")); // Incluído senha apenas para referência
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter cliente por ID: " + e.getMessage());
        }
        return cliente; // Retorna o cliente ou null se não encontrado
    }

    // Método para atualizar os dados do cliente
    public boolean atualizarDados(Cliente cliente) {
        String SQL = "UPDATE CLIENTE SET NOME = ?, ENDERECO = ?, TELEFONE = ?, EMAIL = ?, DATA_NASCIMENTO = ? WHERE CLIENTE_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getEndereco());
            preparedStatement.setString(3, cliente.getTelefone());
            preparedStatement.setString(4, cliente.getEmail());
            preparedStatement.setString(5, cliente.getDataNascimento());
            preparedStatement.setInt(6, cliente.getId()); // Atualização baseada no cliente_id

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Cliente atualizado com sucesso: " + cliente.getNome());
                return true;
            } else {
                System.out.println("Nenhuma linha foi atualizada para o cliente ID: " + cliente.getId());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar dados do cliente: " + e.getMessage());
        }
        return false; // Falha na atualização dos dados
    }
}
