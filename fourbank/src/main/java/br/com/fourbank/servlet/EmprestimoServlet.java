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

            // Criar o objeto Emprestimo
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setClienteId(clienteId);
            emprestimo.setValor(valor);
            emprestimo.setDataEmprestimo(dataEmprestimo);
            emprestimo.setDataVencimento(dataVencimento);
            emprestimo.setParcelas(parcelas);

            // Adicionar o empréstimo
            boolean sucesso = emprestimoDAO.adicionarEmprestimo(emprestimo);

            if (sucesso) {
                // Atualiza a conta
                Conta conta = contaDAO.obterContaPorCliente(clienteId);
                if (conta != null) {
                    double novoSaldo = conta.getSaldo() + valor;
                    conta.setSaldo(novoSaldo);

                    // Atualizar saldo no banco de dados
                    contaDAO.atualizarSaldo(conta);

                    // Atualizar o saldo na sessão
                    request.getSession().setAttribute("saldo", novoSaldo);
                    request.getSession().setAttribute("conta", conta);

                    // Redirecionar para a página inicial ou página de sucesso
                    response.sendRedirect("home.jsp"); // Usando sendRedirect para não enviar o formulário novamente
                } else {
                    response.getWriter().println("Erro: Conta não encontrada para o cliente.");
                }
            } else {
                response.getWriter().println("Falha ao criar o empréstimo.");
            }

        } catch (Exception e) {
            response.getWriter().println("Erro ao processar empréstimo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
