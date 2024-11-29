package br.com.fourbank.servlet;

import br.com.fourbank.dao.CaixinhaDAO;
import br.com.fourbank.dao.ContaDAO;
import br.com.fourbank.model.Caixinha;
import br.com.fourbank.model.Conta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CaixinhaServlet")
public class CaixinhaServlet extends HttpServlet {

    private CaixinhaDAO caixinhaDAO;
    private ContaDAO contaDAO;

    public CaixinhaServlet() {
        this.caixinhaDAO = new CaixinhaDAO();
        this.contaDAO = new ContaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int clienteId = (int) request.getSession().getAttribute("clienteId");
            double valorCaixinha = Double.parseDouble(request.getParameter("valorCaixinha"));

            if (valorCaixinha <= 0) {
                response.getWriter().println("O valor da caixinha deve ser maior que zero.");
                return;
            }

            // Transferir valor para a caixinha
            boolean sucesso = caixinhaDAO.transferirParaCaixinha(clienteId, valorCaixinha);

            if (sucesso) {
                // Atualiza o saldo da conta do cliente
                Conta conta = contaDAO.obterContaPorCliente(clienteId);
                if (conta != null) {
                    double novoSaldo = conta.getSaldo() - valorCaixinha;
                    conta.setSaldo(novoSaldo);

                    // Atualizar saldo da conta no banco de dados
                    contaDAO.atualizarSaldo(conta);

                    // Atualizar o saldo da conta na sessão
                    request.getSession().setAttribute("saldo", novoSaldo);
                    request.getSession().setAttribute("conta", conta);

                    // Atualiza o saldo da caixinha
                    Caixinha caixinha = caixinhaDAO.obterCaixinhaPorCliente(clienteId);
                    if (caixinha != null) {
                        double novoSaldoCaixinha = caixinha.getSaldoCaixinha() + valorCaixinha;
                        caixinha.setSaldoCaixinha(novoSaldoCaixinha);

                        // Atualizar saldo da caixinha no banco de dados
                        caixinhaDAO.atualizarSaldoCaixinha(caixinha);
                    }

                    // Redirecionar para a página inicial ou página de sucesso
                    response.sendRedirect("home.jsp");
                } else {
                    response.getWriter().println("Erro: Conta não encontrada para o cliente.");
                }
            } else {
                response.getWriter().println("Falha ao transferir valor para a caixinha.");
            }

        } catch (Exception e) {
            response.getWriter().println("Erro ao processar caixinha: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
