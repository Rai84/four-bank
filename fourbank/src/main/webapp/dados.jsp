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

    <script src="JS/menu.js"></script>
    <script src="JS/ajuda.js"></script>

    <div class="FormDados">
        <form action="/AbraSuaConta" method="post">
            <div class="dados-box">
                <input type="text" name="nome" id="name" value="<%= cliente.getNome() %>" required>
                <label for="nome">Nome:</label>
            </div>
            <div class="dados-box">
                <input type="text" name="endereco" id="endereco" value="<%= cliente.getEndereco() %>" required>
                <label for="endereco">Endereço:</label>
            </div>
            <div class="dados-box">
                <input type="text" name="telefone" id="telefone" value="<%= cliente.getTelefone() %>" required>
                <label for="telefone">Telefone:</label>
            </div>
            <div class="dados-box">
                <input type="email" name="email" id="email" value="<%= cliente.getEmail() %>" required>
                <label for="email">Email:</label>
            </div>
            <div class="dados-box">
                <input type="text" name="data_nascimento" id="data_nascimento" value="<%= cliente.getDataNascimento() %>" required>
                <label for="data_nascimento">Data de Nascimento:</label>
            </div>
            
            <button type="submit" class="btn-Dados">Atualizar</button>
        </form>
    </div>

</body>
</html>
