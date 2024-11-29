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

            // Verificar se já existe um empréstimo para o cliente
            Emprestimo emprestimoExistente = emprestimoDAO.obterEmprestimoPorCliente(clienteId);
            if (emprestimoExistente != null) {
                response.getWriter().println("Este cliente já possui um empréstimo.");
                return; // Não permite que o cliente faça outro empréstimo
            }

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

                    // Passa o objeto emprestimo para o JSP
                    request.setAttribute("emprestimo", emprestimo);

                    // Redireciona para o JSP
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                } else {
                    request.setAttribute("erro", "Erro: Conta não encontrada para o cliente.");
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("erro", "Falha ao criar o empréstimo.");
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("erro", "Erro ao processar empréstimo: " + e.getMessage());
            request.getRequestDispatcher("home.jsp").forward(request, response);
            e.printStackTrace();
        }
    }
}
