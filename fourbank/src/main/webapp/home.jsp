<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.fourbank.model.Cliente, br.com.fourbank.model.Conta, br.com.fourbank.model.Caixinha
    ,br.com.fourbank.model.Emprestimo" %>

<html lang="pt-br">
<head>
    <link rel="stylesheet" href="css/main.css">
    <title>FourBank</title>
</head>
<body class="tema" id="home">

    <% 
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        Conta conta = (Conta) session.getAttribute("conta");
        Caixinha caixinha = (Caixinha) session.getAttribute("caixinha");  // Adicionando a caixinha
    %>

    <div id="menu-container"></div> 
    <div id="ajuda-container"></div>
    <div id="emprestimo-container"></div>
    <div id="caixinha-container"></div>

    <script src="JS/menu.js"></script>
    <script src="JS/ajuda.js"></script>

    <div class="parent_Home">
        <div class="usuario">
            <% if (cliente != null) { %>
                <p>Bem-vindo,</p>
                <p><%= cliente.getNome() %>!</p>
            <% } else { %>
                <p>Você não está logado. Por favor, <a href="login.jsp">faça login</a>.</p>
            <% } %>
        </div>
        <div class="saldo">
            <p><i class="bi bi-wallet2"></i>Saldo da conta</p>
            <div class="box_Home">
                <% if (conta != null) { %>
                    <p class="txt_saldo">R$: <%= conta.getSaldo() %></p>
                <% } else { %>
                    <p>Você não possui uma conta cadastrada.</p>
                <% } %>
            </div>      
        </div>
        <div class="caixinha">
            <p><i class="bi bi-piggy-bank"></i>Caixinha</p>
            <div class="box_Home">
                <div class="box_caixinha">
                    <div class="box_caixinha2">
                        <h3>Separar valor</h3>
                        <button class="btn_caixinha" class="btn">Separar</button>
                        <script src="JS/caixinha.js"></script> 
                    </div>
                    <div class="caixinha_saldo">
                        <% if (caixinha != null) { %>
                        <h1>Valor separado</h1>
                        <h2>R$: <%= caixinha.getSaldoCaixinha() %></h2>
                        <% } else { %>
                            <h2>Caixinha não encontrada.</h2>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
        <div class="extrato">
            <p><i class="bi bi-wallet2"></i>Extrato</p>
            <div class="box_Home"></div>
        </div>
        <div class="emprestimo">
            <p><i class="bi bi-cash-coin"></i>Empréstimo<button><i class="bi bi-caret-right-fill" ></i></button></i> <span>Juros 7,5%</span></p>
            <div class="box_Home">
                <div class="box_emprestimo">
                    <h2>Valor do limite:</h2> 
                    <div class="box_emprestimo2">
                        <h3>R$: 8000,00</h3>
                        <button class="btn_emprestimo" class="btn">Simular</button>
                        <script src="JS/emprestimo.js"></script> 
                    </div>
                </div>
            </div>
        </div>

        <div class="emprestimo2">
            <p><i class="bi bi-cash-coin"></i>Empréstimo<button><i class="bi bi-caret-left-fill"></i></button></p>
            <div>
                <% 
                // Acessa o objeto emprestimo da sessão, não do request
                Emprestimo emprestimo = (Emprestimo) session.getAttribute("emprestimo");  
                if (emprestimo != null) { 
                %>
                    <p>Valor do Empréstimo: <%= emprestimo.getValor() %></p>
                    <p>Data de Vencimento: <%= emprestimo.getDataVencimento() %></p>
                    <p>Parcelas: <%= emprestimo.getParcelas() %></p>
                <% 
                } else { 
                %>
                    <p>Empréstimo não encontrado.</p>
                <% 
                } 
                %>
            </div>
        </div>

        <div class="btns_principais"> 
            <div class="btn1"><a href="consultarExtrato.jsp"><i class="bi bi-x-diamond"></i></a></div>
            <div class="btn2"><a href="fazerDeposito.jsp"><i class="bi bi-arrow-left-right"></i></a></div>
            <div class="btn3"><a href="fazerEmprestimo.jsp"><i class="bi bi-upc"></i></a></div>
            <div class="btn4"><a href="sair.jsp"><i class="bi bi-phone"></i></a></div>
        </div>
    </div>

</body>
</html>
