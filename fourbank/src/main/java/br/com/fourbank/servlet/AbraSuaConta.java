package br.com.fourbank.servlet;

import br.com.fourbank.dao.ClienteDAO;
import br.com.fourbank.model.Cliente;
import br.com.fourbank.dao.LoginDAO;
import br.com.fourbank.dao.CaixinhaDAO;
import br.com.fourbank.model.Conta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AbraSuaConta")
public class AbraSuaConta extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // Obtendo os parâmetros do formulário
        String nome = req.getParameter("nome");
        String cpf = req.getParameter("cpf");
        String endereco = req.getParameter("endereco");
        String telefone = req.getParameter("telefone");
        String email = req.getParameter("email");
        String dataNascimento = req.getParameter("data_nascimento");
        String senha = req.getParameter("senha");

        // Criando o novo cliente
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(nome);
        novoCliente.setCpf(cpf);
        novoCliente.setEndereco(endereco);
        novoCliente.setTelefone(telefone);
        novoCliente.setEmail(email);
        novoCliente.setDataNascimento(dataNascimento);
        novoCliente.setSenha(senha);

        // Instanciando o DAO de cliente
        ClienteDAO clienteDAO = new ClienteDAO();
        boolean clienteCriado = clienteDAO.criarCliente(novoCliente);

        if (clienteCriado) {
            try {
                // Criando a caixinha para o novo cliente
                CaixinhaDAO caixinhaDAO = new CaixinhaDAO();
                boolean caixinhaCriada = caixinhaDAO.criarCaixinha(novoCliente.getId());

                if (caixinhaCriada) {
                    // Criando a conta para o cliente e redirecionando para a página de login
                    LoginDAO loginDAO = new LoginDAO();
                    Conta novaConta = loginDAO.obterInformacoesConta(novoCliente.getId());
                    req.getSession().setAttribute("conta", novaConta);
                    System.out.println("Conta criada com sucesso!");
                    System.out.println("Caixinha criada com sucesso!");
                    resp.sendRedirect("login.jsp"); // Redireciona para a página de login
                } else {
                    // Caso a caixinha não seja criada
                    req.setAttribute("errorMessage", "Erro ao criar a caixinha!");
                    req.getRequestDispatcher("error.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                // Tratando exceção caso ocorra um erro SQL
                e.printStackTrace();
                req.setAttribute("errorMessage", "Erro ao criar a caixinha: " + e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } else {
            // Caso o cliente não seja criado
            req.setAttribute("errorMessage", "Erro ao criar cliente!");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
