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
                    <button class="btn_fecharEmprestimo"><i class="bi bi-x"></i></button>
                </div>

                <label for="valor">Valor do Empréstimo:</label>   
                <div class="box_FormsEmprestimo">
                    <input type="text" name="valor" id="valor" required>
                    <label for="valor">Min: 500,00 Max: 8000,00</label>
                </div>

                <label for="parcelas">Número de Parcelas:</label>
                <div class="box_FormsEmprestimo">
                    <input type="number" name="parcelas" id="parcelas" min="0" max="20" value="0" required>
                </div>

                <label for="inicio_parcela">Início da Parcela</label>
                <div class="box_FormsEmprestimo">
                    <input type="date" name="inicio_parcela" id="inicio_parcela" required>
                </div>
                <button class="btn_emprestimo2">Continuar</button>
            </form>
        </div>
    </div>
</body>
</html>
