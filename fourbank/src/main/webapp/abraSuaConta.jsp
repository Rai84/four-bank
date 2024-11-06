<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <link rel="stylesheet" href="css/main.css">
    <title>FourBank</title>
</head>
<body class="tema">
    
    <form action="/AbraSuaConta" method="post" onsubmit="return validatePassword();">
        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="name" required>
        
        <label for="cpf">CPF:</label>
        <input type="text" name="cpf" id="cpf" required>
        
        <label for="endereco">Endereço:</label>
        <input type="text" name="endereco" id="endereco" required>
        <label for="telefone">Telefone:</label>
        <input type="text" name="telefone" id="telefone" required>
        <label for="email">Email:</label>
        <input type="email" name="email" id="email" required>
        
        <label for="data_nascimento">Data de Nascimento:</label>
        <input type="text" name="data_nascimento" id="data_nascimento" required>
        <label for="senha">Senha:</label>
        <input type="password" name="senha" id="password" required>
        
        <label for="confirmPassword">Confirme a Senha:</label>
        <input type="password" name="confirmPassword" id="confirmPassword" required> 
        <button type="submit" class="btn-primary1">Cadastrar</button>
    </form>
    
    <script>
        function validatePassword() {
            const password = document.getElementById("password").value;
            const confirmPassword = document.getElementById("confirmPassword").value;
            if (password !== confirmPassword) {
                alert("As senhas não coincidem.");
                return false;
            }
            return true;
        }
    </script>
    

</body>
</html>
