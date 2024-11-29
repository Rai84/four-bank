package br.com.fourbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fourbank.model.Caixinha;
import br.com.fourbank.model.Cliente;
import br.com.fourbank.model.Conta;
import br.com.fourbank.model.Emprestimo;

public class LoginDAO {

    public boolean validarLogin(String cpf, String senha) {
        boolean isValid = false;
        String SQL = "SELECT cliente_id FROM cliente WHERE cpf = ? AND senha = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, senha);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int clienteId = resultSet.getInt("cliente_id");
                    System.out.println("Cliente ID encontrado: " + clienteId);
                    isValid = true;

                    Conta conta = obterInformacoesConta(clienteId);
                    if (conta != null) {
                        System.out.println("Conta encontrada: " + conta.getNumero_conta());
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isValid;
    }

    public Cliente obterInformacoesCliente(String cpf) {
        Cliente cliente = null;
        String SQL = "SELECT * FROM cliente WHERE cpf = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, cpf);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cliente = new Cliente();
                    cliente.setId(resultSet.getInt("cliente_id"));
                    cliente.setNome(resultSet.getString("nome"));
                    cliente.setCpf(resultSet.getString("cpf"));
                    cliente.setEndereco(resultSet.getString("endereco"));
                    cliente.setTelefone(resultSet.getString("telefone"));
                    cliente.setEmail(resultSet.getString("email"));
                    cliente.setDataNascimento(resultSet.getString("data_nascimento"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public Conta obterInformacoesConta(int clienteId) {
        Conta conta = null;
        String SQL = "SELECT * FROM conta WHERE cliente_id = ?";

        System.out.println("Iniciando obtenção de conta para cliente_id: " + clienteId);

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, clienteId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    conta = new Conta(
                            resultSet.getInt("conta_id"),
                            resultSet.getInt("numero_conta"),
                            resultSet.getDouble("saldo"),
                            resultSet.getInt("cliente_id"));
                    System.out.println("Conta encontrada para cliente_id: " + clienteId);
                    System.out
                            .println("Detalhes da conta: " + conta.getNumero_conta() + " | Saldo: " + conta.getSaldo());
                } else {
                    System.out.println("Conta não encontrada para o cliente_id: " + clienteId);
                    System.out.println("Erro aqui: Não há conta associada ao cliente_id: " + clienteId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter informações da conta: " + e.getMessage());
            e.printStackTrace();
        }

        return conta;
    }

    public Caixinha obterInformacoesCaixinha(int clienteId) {
        Caixinha caixinha = null;
        String SQL = "SELECT * FROM caixinha WHERE cliente_id = ?";

        System.out.println("Iniciando obtenção de caixinha para cliente_id: " + clienteId);

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, clienteId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    caixinha = new Caixinha(
                            resultSet.getInt("caixinha_id"),
                            resultSet.getInt("cliente_id"),
                            resultSet.getDouble("saldoCaixinha"));
                    System.out.println("Caixinha encontrada para cliente_id: " + clienteId);
                    System.out.println("Detalhes da caixinha: " + caixinha.getCaixinhaId() + " | Saldo: "
                            + caixinha.getSaldoCaixinha());
                } else {
                    System.out.println("Caixinha não encontrada para o cliente_id: " + clienteId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter informações da caixinha: " + e.getMessage());
            e.printStackTrace();
        }

        return caixinha;
    }

    public Emprestimo obterEmprestimoPorCliente(int clienteId) {
        Emprestimo emprestimo = null;
        String SQL = "SELECT * FROM EMPRESTIMO WHERE cliente_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, clienteId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                emprestimo = new Emprestimo();
                emprestimo.setClienteId(resultSet.getInt("cliente_id"));
                emprestimo.setValor(resultSet.getDouble("valor_emprestimo"));
                emprestimo.setDataEmprestimo(resultSet.getDate("data_emprestimo"));
                emprestimo.setDataVencimento(resultSet.getDate("data_vencimento"));
                emprestimo.setParcelas(resultSet.getInt("parcelas"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprestimo;
    }

    // Método para inserir caixinha
    public boolean inserirCaixinha(int clienteId) throws SQLException {
        // Verifica se já existe uma caixinha para o cliente
        if (caixinhaExistente(clienteId)) {
            System.out.println("Caixinha já existente para o cliente_id: " + clienteId);
            return false; // Não cria a caixinha, pois ela já existe
        }

        // Caso não exista, insere uma nova caixinha
        String SQL = "INSERT INTO caixinha (cliente_id, saldoCaixinha) VALUES (?, 0)"; // Inicializa com saldo 0

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, clienteId);
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0; // Retorna true se a inserção for bem-sucedida
        }
    }

    // Método para verificar se a caixinha já existe para o cliente
    private boolean caixinhaExistente(int clienteId) throws SQLException {
        String SQL = "SELECT 1 FROM caixinha WHERE cliente_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, clienteId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Retorna true se a caixinha já existir
            }
        }
    }

    @Override
    public String toString() {
        return "LoginDAO []";
    }
}
