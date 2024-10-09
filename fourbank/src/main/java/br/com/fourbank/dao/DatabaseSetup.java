package br.com.fourbank.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void createTables() {
        String SQL = "CREATE TABLE IF NOT EXISTS CLIENTE ("
                + "ID INT PRIMARY KEY AUTO_INCREMENT,"
                + "NOME VARCHAR(255),"
                + "CPF VARCHAR(11),"
                + "ENDERECO VARCHAR(255),"
                + "TELEFONE VARCHAR(20),"
                + "EMAIL VARCHAR(255),"
                + "DATA_NASCIMENTO DATE,"
                + "SENHA VARCHAR(255)"
                + ");";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(SQL);
            System.out.println("Tabela CLIENTE verificada/criada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar/verificar tabelas!");
            e.printStackTrace();
        }
    }
}
