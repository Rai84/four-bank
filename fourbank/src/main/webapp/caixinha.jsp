<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Simulação de Empréstimo</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
    <div class="fundo_caixinha">
        <div class="FormsCaixinha">
            <form action="CaixinhaServlet" method="post">
                <div class="box_TxtCaixinha">
                    <h2>Guardar na caixinha</h2>
                    <button type="button" class="btn_fecharCaixinha"><i class="bi bi-x"></i></button>
                </div>

                <label for="valor">Valor para Transferir:</label>
                <div class="box_FormsCaixinha">
                    <input type="text" name="valorCaixinha" id="valorCaixinha" required>
                    <label for="valor" class="form-label">Valor do Empréstimo:</label>
                </div>

                <!-- Campo oculto para o cliente_id -->
                <input type="hidden" name="cliente_id" value="<%= session.getAttribute("clienteId") %>">
                <button type="submit" class="btn_Caixinha2">Continuar</button>
            </form>
        </div>
    </div>
</body>     
</html>
