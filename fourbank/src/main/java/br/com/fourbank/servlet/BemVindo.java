package br.com.fourbank.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BemVindo")
public class BemVindo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        String action = req.getParameter("action");

        if ("Faca Login".equals(action)) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else if ("Abra a sua conta".equals(action)) {
            req.getRequestDispatcher("abraSuaConta.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Ação não encontrada");
        }
    }
}
