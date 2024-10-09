package br.com.fourbank.servlet;

import br.com.fourbank.dao.clienteDAO;
import br.com.fourbank.model.cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/AbraSuaContaTeste")
public class AbraSuaConta extends HttpServlet {

    @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("name");
        String cpf = req.getParameter("cpf");
        String endereco = req.getParameter("endereco");
        String telefone = req.getParameter("telefone");
        String email = req.getParameter("email");
        String dataNascimento = req.getParameter("data_nascimento");
        String senha = req.getParameter("senha");

        cliente novoCliente = new cliente();
        novoCliente.setName(nome);
        novoCliente.setCpf(cpf);
        novoCliente.setEndereco(endereco);
        novoCliente.setTelefone(telefone);
        novoCliente.setEmail(email);
        novoCliente.setDataNascimento(dataNascimento);
        novoCliente.setSenha(senha);

        // Chama o método de inserção
        clienteDAO dao = new clienteDAO();
        dao.insert(novoCliente);

        // Redireciona para a página de login após a inserção
        req.getRequestDispatcher("login.html").forward(req, resp);
    }

}


