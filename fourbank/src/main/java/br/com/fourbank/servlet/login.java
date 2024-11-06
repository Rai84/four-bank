package br.com.fourbank.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import br.com.fourbank.model.Cliente;
import br.com.fourbank.model.Conta;
import br.com.fourbank.dao.LoginDAO;

@WebServlet("/Login")
public class Login extends HttpServlet {

    private LoginDAO dao = new LoginDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String cpf = req.getParameter("cpf");
        String senha = req.getParameter("senha");

        if (dao.validarLogin(cpf, senha)) {
            Cliente cliente = dao.obterInformacoesCliente(cpf);
            Conta conta = dao.obterInformacoesConta(cpf);
            
            HttpSession session = req.getSession();
            session.setAttribute("cliente", cliente);
            session.setAttribute("conta", conta);
            
            resp.sendRedirect("home.jsp");
        } else {
            req.setAttribute("errorMessage", "CPF ou senha inválidos.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
