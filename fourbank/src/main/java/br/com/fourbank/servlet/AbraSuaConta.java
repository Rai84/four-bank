package br.com.fourbank.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import br.com.fourbank.dao.ClienteDAO;
import br.com.fourbank.dao.ContaDAO;
import br.com.fourbank.model.Cliente;
import br.com.fourbank.model.Conta;
import br.com.fourbank.dao.LoginDAO;

@WebServlet("/AbraSuaConta")
public class AbraSuaConta extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String nome = req.getParameter("nome");
        String cpf = req.getParameter("cpf");
        String endereco = req.getParameter("endereco");
        String telefone = req.getParameter("telefone");
        String email = req.getParameter("email");
        String dataNascimento = req.getParameter("data_nascimento");
        String senha = req.getParameter("senha");

        Cliente novoCliente = new Cliente();
        novoCliente.setNome(nome);
        novoCliente.setCpf(cpf);
        novoCliente.setEndereco(endereco);
        novoCliente.setTelefone(telefone);
        novoCliente.setEmail(email);
        novoCliente.setDataNascimento(dataNascimento);
        novoCliente.setSenha(senha);

        ClienteDAO clienteDAO = new ClienteDAO();
        boolean clienteCriado = clienteDAO.criarCliente(novoCliente); // Verifique se o método está implementado corretamente

        if (clienteCriado) {
            ContaDAO contaDAO = new ContaDAO();
            if (contaDAO.verificarCpfExistente(novoCliente.getCpf())) {
                contaDAO.createAccountForClient(novoCliente.getCpf());
                
                // Cria uma nova instância do LoginDAO
                LoginDAO loginDAO = new LoginDAO();
                Conta novaConta = loginDAO.obterInformacoesConta(novoCliente.getCpf());
                req.getSession().setAttribute("conta", novaConta);
                
                // Redireciona para a página de login ou onde for necessário
                resp.sendRedirect("login.jsp");
            } else {
                req.setAttribute("errorMessage", "CPF não encontrado para criar conta!");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMessage", "Erro ao criar cliente!");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
