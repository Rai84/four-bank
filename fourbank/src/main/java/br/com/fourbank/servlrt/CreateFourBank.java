package br.com.fourbank.servlrt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateFourBank extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fourName = req.getParameter("four-name");
        System.out.println(fourName);
        req.getRequestDispatcher("index.html").forward(req, resp);

    }
}
