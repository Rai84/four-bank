package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseManager;
import model.Cliente;

public class ClienteRepository {
    private Connection conexao = DatabaseManager.getConexao();

    public Cliente buscarPorId(int id) { // buscar cliente por ID no banco de dados
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(rs.getString("nome"), rs.getString("documento"), rs.getString("telefone"), rs.getString("email"), rs.getString("endereco"), rs.getString("data_nascimento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


