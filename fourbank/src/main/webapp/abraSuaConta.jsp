<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <link rel="stylesheet" href="css/main.css">
    <title>FourBank</title>
</head>
<body class="tema">
    <div class="FormConta">
        <h2>Abra sua Conta</h2>
        <form action="/AbraSuaConta" method="post" onsubmit="return validatePassword();">

            <div class="conta-box">
                <input type="text" name="nome" id="name" required>
                <label for="nome">Nome:</label>
            </div>
            <div class="conta-box">
                <input type="text" name="cpf" id="cpf" required>
                <label for="cpf">CPF:</label>
            </div>
            <div class="conta-box">
                <input type="text" name="endereco" id="endereco" required>
                <label for="endereco">Endereço:</label>
            </div>
            <div class="conta-box">
                <input type="text" name="telefone" id="telefone" required>
                <label for="telefone">Telefone:</label>
            </div>
            <div class="conta-box">
                <input type="email" name="email" id="email" required>  
                <label for="email">Email:</label>
            </div>
            <div class="conta-box">
                <input type="text" name="data_nascimento" id="data_nascimento" required>
                <label for="data_nascimento">Data de Nascimento:</label>
            </div>
            <div class="conta-box">
                <input type="password" name="senha" id="password" required>
                <label for="senha">Senha:</label>
            </div>
            <div class="conta-box">
                <input type="password" name="confirmPassword" id="confirmPassword" required> 
                <label for="confirmPassword">Confirme a Senha:</label>
            </div>
                 
            <button type="submit" class="btn-abraConta">Cadastrar</button>
        </form>
    </div>
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
