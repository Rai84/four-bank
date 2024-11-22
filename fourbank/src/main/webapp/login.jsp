<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <link rel="stylesheet" href="css/main.css">
    <title>FourBank</title>
  </head>
  <body class="tema">
    <div class="login">
      <h2>Login</h2>
      <form action="/Login" method="post">
      <div class="login-box">
        <input type="text" name="cpf" id="cpf" required>
        <label for="cpf">CPF:</label>
      </div>
      <div class="login-box">
        <input type="password" name="senha" id="senha" required>
        <label for="senha">Senha:</label>
      </div>
      <button type="submit" class="btn-login">Entrar</button>
      </form>
    </div>
  </body>
</html>  