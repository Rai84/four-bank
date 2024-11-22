<!DOCTYPE html>
<html lang="pt-br">
<head>
    <link rel="stylesheet" href="css/main.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <div class="fundo_emprestimo">
        <div class="FormsEmprestimo">
            <form action="" method="post">
                <h2>Simulação de Empréstimo</h2>
                
                <label for="valor">Valor do Empréstimo:</label>
                <div class="box_FormsEmprestimo">
                    
                    <input type="text" name="valor" id="valor" required>
                    <label for="valor">Min: 500,00 Max: 8000,00</label>
                </div>
                <label for="parcelas">Número de Parcelas:</label>
                <div class="box_FormsEmprestimo">
                    <input type="number" name="parcelas" id="parcelas" min="0" max="20" value="0" required>
                    <label for="parcelas"></label>
                </div>
                <label for="inicio_parcela">Inicio parcela</label>
                <div class="box_FormsEmprestimo">
                    <input type="date" name="inicio_parcela" id="inicio_parcela" required>
                    <label for="inicio_parcela"></label>
                    <script src="JS/emprestimo.js"></script>
                </div>
                <button class="btn_emprestimo2">Solicitar</button>
                
            </form>
        </div>
    </div>
</body>