package br.com.fourbank.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.fourbank.dao.ClienteDAO;
import br.com.fourbank.model.Cliente;

@WebServlet("/AtualizarDados")
public class AtualizarDadosServlet extends HttpServlet {

    private ClienteDAO clienteDAO;

    public AtualizarDadosServlet() {
        this.clienteDAO = new ClienteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obter clienteId da sessão
            Integer clienteId = (Integer) request.getSession().getAttribute("clienteId");
            System.out.println("clienteId da sessão: " + clienteId);

            if (clienteId == null) {
                response.sendRedirect("login.jsp"); // Se não estiver logado, redireciona para login
                return;
            }

            // Pegando os dados do formulário
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            String telefone = request.getParameter("telefone");
            String email = request.getParameter("email");
            String dataNascimento = request.getParameter("data_nascimento");

            // Obter o cliente do banco de dados
            Cliente cliente = clienteDAO.obterClientePorId(clienteId);
            System.out.println("Cliente encontrado: " + (cliente != null ? cliente.getNome() : "Não encontrado"));

            if (cliente != null) {
                // Atualiza os dados permitidos (não atualizando senha, cpf ou cliente_id)
                cliente.setNome(nome);
                cliente.setEndereco(endereco);
                cliente.setTelefone(telefone);
                cliente.setEmail(email);
                cliente.setDataNascimento(dataNascimento);

                // Atualiza os dados no banco de dados
                boolean sucesso = clienteDAO.atualizarDados(cliente);

                if (sucesso) {
                    // Atualiza os dados na sessão do cliente
                    request.getSession().setAttribute("cliente", cliente);
                    response.sendRedirect("home.jsp"); // Redireciona para a página principal após a atualização
                } else {
                    response.getWriter().println("Erro ao atualizar os dados.");
                }
            } else {
                response.getWriter().println("Cliente não encontrado.");
            }
        } catch (Exception e) {
            response.getWriter().println("Erro ao processar atualização: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
