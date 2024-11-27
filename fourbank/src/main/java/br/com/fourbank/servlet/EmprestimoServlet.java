package br.com.fourbank.servlet;

import br.com.fourbank.dao.EmprestimoDAO;
import br.com.fourbank.model.Emprestimo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/EmprestimoServlet")
public class EmprestimoServlet extends HttpServlet {

    private EmprestimoDAO emprestimoDAO;

    // Construtor
    public EmprestimoServlet() {
        this.emprestimoDAO = new EmprestimoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperar os parâmetros do formulário ou da requisição
        try {
            int clienteId = Integer.parseInt(request.getParameter("cliente_id"));
            double valor = Double.parseDouble(request.getParameter("valor"));
            Date dataEmprestimo = Date.valueOf(request.getParameter("data_emprestimo"));
            Date dataVencimento = Date.valueOf(request.getParameter("data_vencimento"));
            int parcelas = Integer.parseInt(request.getParameter("parcelas"));

            // Validar os dados do empréstimo
            if (valor <= 0) {
                response.getWriter().println("O valor do empréstimo deve ser maior que zero.");
                return;
            }

            // Criar o objeto Emprestimo
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setClienteId(clienteId);
            emprestimo.setValor(valor);
            emprestimo.setDataEmprestimo(dataEmprestimo);
            emprestimo.setDataVencimento(dataVencimento);
            emprestimo.setParcelas(parcelas);
            emprestimo.setStatus("PENDENTE");

            // Chamar o DAO para adicionar o empréstimo no banco
            boolean sucesso = emprestimoDAO.adicionarEmprestimo(emprestimo);

            // Responder ao cliente com a operação
            if (sucesso) {
                response.getWriter().println("Empréstimo criado com sucesso!");
                response.sendRedirect("home.jsp");
            } else {
                response.getWriter().println("Falha ao criar o empréstimo.");
            }
        } catch (Exception e) {
            response.getWriter().println("Erro ao processar empréstimo: " + e.getMessage());
        }
    }
}
