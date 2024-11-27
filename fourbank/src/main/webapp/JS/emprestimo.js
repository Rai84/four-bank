document.addEventListener("DOMContentLoaded", function () {
    // Carregar o conteúdo do arquivo "emprestimo.jsp"
    fetch("emprestimo.jsp") 
        .then((response) => {
            if (!response.ok) {
                throw new Error("Erro ao carregar a tela emprestimo");
            }
            return response.text();
        })
        .then((data) => {
            document.getElementById("emprestimo-container").innerHTML = data;

            // Obtenha a data atual após o conteúdo ser inserido
            const hoje = new Date();
            const anoAtual = hoje.getFullYear();
            const mesAtual = String(hoje.getMonth() + 1).padStart(2, '0');
            const diaAtual = String(hoje.getDate()).padStart(2, '0');
            const dataMinima = `${anoAtual}-${mesAtual}-${diaAtual}`;

            const dataMaxima = new Date(hoje);
            dataMaxima.setMonth(dataMaxima.getMonth() + 2);
            const anoMaximo = dataMaxima.getFullYear();
            const mesMaximo = String(dataMaxima.getMonth() + 1).padStart(2, '0');
            const diaMaximo = String(dataMaxima.getDate()).padStart(2, '0');
            const dataMaximaFormatada = `${anoMaximo}-${mesMaximo}-${diaMaximo}`;

            // Defina os valores para o input de data
            const inputData = document.getElementById('inicio_parcela');
            if (inputData) {
                inputData.min = dataMinima;
                inputData.max = dataMaximaFormatada;
            }

            // Adicionar evento de clique para mover o fundo do empréstimo (dentro do `then`)
            const btnEmprestimo = document.querySelector('.btn_emprestimo');
            const fundoEmprestimo = document.querySelector('.fundo_emprestimo');
            const btn_fecharEmprestimo = document.querySelector('.btn_fecharEmprestimo');

            if (btnEmprestimo && fundoEmprestimo) {
                btnEmprestimo.addEventListener('click', function() {
                    fundoEmprestimo.classList.add('ativo');
                }); 
            }
            if (btn_fecharEmprestimo && fundoEmprestimo) {
                btn_fecharEmprestimo.addEventListener('click', function() {
                    fundoEmprestimo.classList.remove('ativo');
                });
            }
        })
        .catch((error) => console.error("Erro ao carregar a tela emprestimo:", error));
});
