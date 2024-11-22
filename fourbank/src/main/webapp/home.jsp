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
            <% if (conta != null) { %>
                <p><i class="bi bi-wallet2"></i>Seu saldo é:</p>
                <p><%= conta.getSaldo() %></p>
            <% } else { %>
                <p>Você não possui uma conta cadastrada.</p>
            <% } %>
        </div>
        <div class="caixinha"></div>
        <div class="extrato"></div>
        <div class="emprestimo">

            <!-- <script src="JS/emprestimo.js"></script> -->
        </div>
        <div class="btns_principais"> 
            <div class="btn1"><a href="consultarExtrato.jsp"></a></div>
            <div class="btn2"><a href="fazerDeposito.jsp"></a></div>
            <div class="btn3"><a href="fazerEmprestimo.jsp"></a></div>
            <div class="btn4"><a href="sair.jsp"></a></div>
        </div>
    </div>

</body>
</html>
