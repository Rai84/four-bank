package br.com.fourbank.servlet;

import br.com.fourbank.dao.LoginDAO;
import br.com.fourbank.model.Cliente;
import br.com.fourbank.model.Conta;
import br.com.fourbank.model.Emprestimo;
import br.com.fourbank.model.Caixinha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    private LoginDAO dao = new LoginDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String cpf = req.getParameter("cpf");
        String senha = req.getParameter("senha");
        System.out.println("Recebido CPF: " + cpf + " | Senha: " + senha);

        if (dao.validarLogin(cpf, senha)) {
            System.out.println("Login válido para CPF: " + cpf);

            Cliente cliente = dao.obterInformacoesCliente(cpf);
            if (cliente == null) {
                req.setAttribute("errorMessage", "Cliente não encontrado.");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
                return;
            }

            Conta conta = dao.obterInformacoesConta(cliente.getId());
            if (conta == null) {
                req.setAttribute("errorMessage", "Conta não encontrada.");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
                return;
            }

            Caixinha caixinha = dao.obterInformacoesCaixinha(cliente.getId());
            if (caixinha == null) {
                try {
                    boolean caixinhaCriada = dao.inserirCaixinha(cliente.getId());
                    if (caixinhaCriada) {
                        System.out.println("Caixinha criada com sucesso para o cliente ID: " + cliente.getId());
                        caixinha = dao.obterInformacoesCaixinha(cliente.getId());
                    }
                } catch (SQLException e) {
                    System.out.println("Erro ao tentar criar a caixinha: " + e.getMessage());
                    req.setAttribute("errorMessage", "Erro ao criar caixinha.");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                    return;
                }
            }

            Emprestimo emprestimo = dao.obterEmprestimoPorCliente(cliente.getId());
            if (emprestimo == null) {
                System.out.println("Cliente ainda não possui empréstimos.");
            } else {
                System.out.println("Empréstimo encontrado para cliente ID: " + cliente.getId());
                System.out.println("Valor: " + emprestimo.getValor());
                System.out.println("Data empréstimo: " + emprestimo.getDataEmprestimo());
                System.out.println("Data vencimento: " + emprestimo.getDataVencimento());
                System.out.println("Parcelas: " + emprestimo.getParcelas());

                req.setAttribute("emprestimo", emprestimo); // Armazenando no request
            }

            HttpSession session = req.getSession();
            session.setAttribute("clienteId", cliente.getId());
            session.setAttribute("cliente", cliente);
            session.setAttribute("conta", conta);
            session.setAttribute("caixinha", caixinha);
            session.setAttribute("emprestimo", emprestimo); // Salvar na sessão também

            req.getRequestDispatcher("/home.jsp").forward(req, resp); // Usar forward aqui

        } else {
            req.setAttribute("errorMessage", "CPF ou senha inválidos.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
