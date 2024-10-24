package br.com.fourbank.dao;

import br.com.fourbank.model.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ClienteDAO {
    public void insert(Cliente c) {
        String SQL = "INSERT INTO CLIENTE (NOME, CPF, ENDERECO, TELEFONE, EMAIL, DATA_NASCIMENTO, SENHA) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "admin", "admin");
            System.out.println("Conectado ao banco de dados com sucesso!");
    
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
    
            preparedStatement.setString(1, c.getNome());
            preparedStatement.setString(2, c.getCpf());
            preparedStatement.setString(3, c.getEndereco());
            preparedStatement.setString(4, c.getTelefone());
            preparedStatement.setString(5, c.getEmail());
            preparedStatement.setString(6, c.getDataNascimento());
            preparedStatement.setString(7, c.getSenha());
    
            System.out.println("Inserindo dados no banco de dados...");
            preparedStatement.executeUpdate(); // Execute a inserção
    
            System.out.println("Dados inseridos com sucesso!");
    
            preparedStatement.close(); // Feche a declaração
            connection.close(); // Feche a conexão
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
        }
    }
    

}
