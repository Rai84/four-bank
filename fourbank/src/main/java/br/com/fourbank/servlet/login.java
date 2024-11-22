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

    private static final String HOME_PAGE = "home.jsp";
    private static final String LOGIN_PAGE = "login.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // Recebe os parâmetros do formulário
        String cpf = req.getParameter("cpf");
        String senha = req.getParameter("senha");

        // Tenta validar o login
        if (dao.validarLogin(cpf, senha)) {
            // Obtemos o cliente
            Cliente cliente = dao.obterInformacoesCliente(cpf);
            if (cliente == null) {
                req.setAttribute("errorMessage", "Cliente não encontrado.");
                req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
                return;
            }

            // Verifica o ID do cliente
            System.out.println("Cliente ID encontrado: " + cliente.getId()); // Verifique se o ID do cliente é 4, como
                                                                             // esperado

            // Obtemos as informações da conta usando o clienteId
            Conta conta = dao.obterInformacoesConta(cliente.getId());
            if (conta == null) {
                req.setAttribute("errorMessage", "Conta não encontrada.");
                req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
                return;
            }

            // Iniciamos a sessão e armazenamos os dados
            HttpSession session = req.getSession();
            session.setAttribute("cliente", cliente);
            session.setAttribute("conta", conta);

            // Redireciona para a página principal
            resp.sendRedirect(HOME_PAGE);
        } else {
            // Se o login for inválido
            req.setAttribute("errorMessage", "CPF ou senha inválidos.");
            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        }
    }
}
