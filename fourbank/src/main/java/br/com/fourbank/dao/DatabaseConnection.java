package br.com.fourbank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/fourbank"; // Altere "seu_banco_de_dados"
    private static final String USER = "root"; // Substitua "seu_usuario" pelo seu nome de usuário
    private static final String PASSWORD = ""; // Substitua "sua_senha" pela sua senha

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
