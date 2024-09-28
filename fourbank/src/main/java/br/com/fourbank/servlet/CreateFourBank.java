package br.com.fourbank.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CreateFourBankTest")

public class CreateFourBank extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("Faça Login".equals(action)) {
            req.getRequestDispatcher("login.html").forward(req, resp);
        } else if ("Abra a sua conta".equals(action)) {
            req.getRequestDispatcher("abraSuaConta.html").forward(req, resp);
        }
    }

}

