// package br.com.fourbank.servlet;

// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import br.com.fourbank.dao.LoginDAO;
// import br.com.fourbank.model.Conta;
// import br.com.fourbank.model.Emprestimo;

// import java.io.IOException;
// import java.sql.SQLException;
// import java.util.logging.Level;
// import java.util.logging.Logger;

// @WebServlet("/PagarEmprestimo")
// public class PagarEmprestimoServlet extends HttpServlet {
//     private static final Logger logger = Logger.getLogger(PagarEmprestimoServlet.class.getName());
//     private LoginDAO dao = new LoginDAO();

//     @Override
//     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String clienteIdStr = req.getParameter("clienteId");
//         String valorParcelaStr = req.getParameter("valorParcela");

//         try {
//             if (clienteIdStr == null || valorParcelaStr == null) {
//                 throw new IllegalArgumentException("Parâmetros inválidos.");
//             }

//             int clienteId = Integer.parseInt(clienteIdStr);
//             double valorParcela = Double.parseDouble(valorParcelaStr);

//             Emprestimo emprestimo = dao.obterEmprestimoPorCliente(clienteId);
//             Conta conta = dao.obterInformacoesConta(clienteId);

//             if (emprestimo == null || conta == null) {
//                 resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                 resp.getWriter().write("Empréstimo ou conta não encontrada.");
//                 return;
//             }

//             if (conta.getSaldo() >= valorParcela) {
//                 conta.setSaldo(conta.getSaldo() - valorParcela);
//                 dao.atualizarSaldoConta(conta);

//                 emprestimo.setParcelas(emprestimo.getParcelas() - 1);
//                 emprestimo.setDataVencimento(new java.sql.Date(adicionarMes(emprestimo.getDataVencimento()).getTime()));
//                 dao.atualizarEmprestimo(emprestimo);

//                 resp.setStatus(HttpServletResponse.SC_OK);
//                 req.getRequestDispatcher("/home.jsp").forward(req, resp);
//             } else {
//                 resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                 resp.getWriter().write("Saldo insuficiente.");
//             }
//         } catch (NumberFormatException e) {
//             resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//             resp.getWriter().write("Formato inválido para clienteId ou valorParcela.");
//         } catch (IllegalArgumentException e) {
//             resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//             resp.getWriter().write(e.getMessage());
//         } catch (SQLException e) {
//             logger.log(Level.SEVERE, "Erro ao acessar o banco de dados", e);
//             resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//             resp.getWriter().write("Erro ao acessar o banco de dados: " + e.getMessage());
//         }
//     }

//     private java.util.Date adicionarMes(java.util.Date data) {
//         java.util.Calendar cal = java.util.Calendar.getInstance();
//         cal.setTime(data);
//         cal.add(java.util.Calendar.MONTH, 1);
//         return cal.getTime();
//     }
// }
