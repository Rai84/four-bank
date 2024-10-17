import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import br.com.fourbank.dao.loginDAO;

@WebServlet("/Login")
public class login extends HttpServlet {

    private loginDAO dao = new loginDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String cpf = req.getParameter("cpf");
        String senha = req.getParameter("senha");

        if (dao.validarLogin(cpf, senha)) {
            resp.sendRedirect("home.html");
        } else {
            resp.sendRedirect("login.html");
        }
    }
}
