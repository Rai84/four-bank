package br.com.fourbank.servlet;

import br.com.fourbank.dao.loginDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class login extends HttpServlet {
    
    private loginDAO dao = new loginDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cpf = req.getParameter("cpf");
        String senha = req.getParameter("senha");

        if (dao.validarLogin(cpf, senha)) {
            resp.sendRedirect("home.jsp");
        } else {
            resp.sendRedirect("login.jsp");
        }
    }
}
