<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Simulação de Empréstimo</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
    <div class="fundo_emprestimo">
        <div class="FormsEmprestimo">
            <form action="EmprestimoServlet" method="post">
                <div class="box_TxtEmprestimo">
                    <h2>Simulação de Empréstimo</h2>
                    <button type="button" class="btn_fecharEmprestimo"><i class="bi bi-x"></i></button>
                </div>

                <!-- Valor do Empréstimo -->
                <label for="valor">Valor do Empréstimo:</label>   
                <div class="box_FormsEmprestimo">
                    <input type="number" name="valor" id="valor" min="500" max="8000" step="0.01" required>
                    <label for="valor">Min: R$ 500,00 Max: R$ 8.000,00</label>
                </div>

                <!-- Número de Parcelas -->
                <label for="parcelas">Número de Parcelas:</label>
                <div class="box_FormsEmprestimo">
                    <input type="number" name="parcelas" id="parcelas" min="1" max="20" value="1" required>
                </div>

                <!-- Data de Vencimento -->
                <label for="data_vencimento">Data de Vencimento:</label>
                <div class="box_FormsEmprestimo">
                    <input type="date" name="data_vencimento" id="data_vencimento" required>
                </div>

                <!-- Campo oculto para o cliente_id -->
                <input type="hidden" name="cliente_id" value="<%= session.getAttribute("clienteId") %>">
                <button type="submit" class="btn_emprestimo2">Continuar</button>
            </form>
        </div>
    </div>
</body>
</html>
