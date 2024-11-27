<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.fourbank.model.Cliente, br.com.fourbank.model.Conta" %>

<html lang="pt-br">
<head>
    <link rel="stylesheet" href="css/main.css">
    <title>FourBank</title>
</head>
<body class="tema" id="home">

    <% 
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        Conta conta = (Conta) session.getAttribute("conta");
    %>

    <div id="menu-container"></div> 
    <div id="ajuda-container"></div>
    <div id="emprestimo-container"></div>

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
            <p><i class="bi bi-wallet2"></i>Saldo da conta <i class="bi bi-x-circle"></i></p>
            <div class="box_Home">
                <% if (conta != null) { %>
                    <p>R$: <%= conta.getSaldo() %></p>
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
                        <% if (conta != null) { %>
                            <h1>Valor separado</h1>
                            <h2>R$: <%= conta.getSaldo() %></h2>
                        <% } else { %>
                            <h2>err.</h2>
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
            <p><i class="bi bi-cash-coin"></i>emprestimo<span>Juros 7,5%</span></p>
            <div class="box_Home">
                <h2 class="txt_Valor">Valor do limite:</h2> 
                <div class="box_emprestimo">
                    <h3>R$: 8000,00</h3>
                    <button class="btn_emprestimo" class="btn">Simular</button>
                    <script src="JS/emprestimo.js"></script> 
                </div>
                
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
