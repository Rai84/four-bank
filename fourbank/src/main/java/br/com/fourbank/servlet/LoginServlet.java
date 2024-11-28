package br.com.fourbank.servlet;

import br.com.fourbank.dao.LoginDAO;
import br.com.fourbank.model.Cliente;
import br.com.fourbank.model.Conta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    private LoginDAO dao = new LoginDAO();

    private static final String HOME_PAGE = "home.jsp";
    private static final String LOGIN_PAGE = "login.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String cpf = req.getParameter("cpf");
        String senha = req.getParameter("senha");

        if (dao.validarLogin(cpf, senha)) {
            Cliente cliente = dao.obterInformacoesCliente(cpf);
            if (cliente == null) {
                req.setAttribute("errorMessage", "Cliente não encontrado.");
                req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
                return;
            }

            System.out.println("Cliente ID encontrado: " + cliente.getId());

            Conta conta = dao.obterInformacoesConta(cliente.getId());
            if (conta == null) {
                req.setAttribute("errorMessage", "Conta não encontrada.");
                req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
                return;
            }

            HttpSession session = req.getSession();
            session.setAttribute("clienteId", cliente.getId());
            session.setAttribute("cliente", cliente);
            session.setAttribute("conta", conta);

            resp.sendRedirect(HOME_PAGE);
        } else {
            req.setAttribute("errorMessage", "CPF ou senha inválidos.");
            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        }
    }
}
