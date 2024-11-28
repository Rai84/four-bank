package br.com.fourbank.servlet;

import br.com.fourbank.dao.ContaDAO;
import br.com.fourbank.dao.EmprestimoDAO;
import br.com.fourbank.model.Conta;
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
    private ContaDAO contaDAO;

    public EmprestimoServlet() {
        this.emprestimoDAO = new EmprestimoDAO();
        this.contaDAO = new ContaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int clienteId = (int) request.getSession().getAttribute("clienteId");
            double valor = Double.parseDouble(request.getParameter("valor"));
            Date dataVencimento = Date.valueOf(request.getParameter("data_vencimento"));
            int parcelas = Integer.parseInt(request.getParameter("parcelas"));

            Date dataEmprestimo = new Date(System.currentTimeMillis());

            if (valor <= 0) {
                response.getWriter().println("O valor do empréstimo deve ser maior que zero.");
                return;
            }

            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setClienteId(clienteId);
            emprestimo.setValor(valor);
            emprestimo.setDataEmprestimo(dataEmprestimo);
            emprestimo.setDataVencimento(dataVencimento);
            emprestimo.setParcelas(parcelas);

            boolean sucesso = emprestimoDAO.adicionarEmprestimo(emprestimo);

            if (sucesso) {
                Conta conta = contaDAO.obterContaPorCliente(clienteId);
                if (conta != null) {
                    double novoSaldo = conta.getSaldo() + valor;
                    conta.setSaldo(novoSaldo);

                    contaDAO.atualizarSaldo(conta);
                    request.getSession().setAttribute("saldo", novoSaldo);
                    request.getSession().setAttribute("conta", conta);

                    response.getWriter().println("Empréstimo criado com sucesso! Saldo atualizado.");
                } else {
                    response.getWriter().println("Erro: Conta não encontrada para o cliente.");
                }
            } else {
                response.getWriter().println("Falha ao criar o empréstimo.");
            }

            request.getRequestDispatcher("/home.jsp").forward(request, response);
            
        } catch (Exception e) {
            response.getWriter().println("Erro ao processar empréstimo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
