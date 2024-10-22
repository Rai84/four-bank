package br.com.fourbank.dao;

import br.com.fourbank.model.Transacao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransacaoDAO {
    public void insert(Transacao c) {
        String SQL = "INSERT INTO CLIENTE (ID_DESTINO, TIPO_TRANSACAO, VALOR, DATA_TRANSACAO) VALUES (?, ?, ?, ?)";
    
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "admin", "admin");
            System.out.println("Conectado ao banco de dados com sucesso!");
    
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
    
            preparedStatement.setInt(1, c.getIdDestino());
            preparedStatement.setString(2, c.getTipo());
            preparedStatement.setInt(3, c.getIdDestino());
            preparedStatement.setDouble(4, c.getValor());
            
    
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
