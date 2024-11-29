document.addEventListener("DOMContentLoaded", () => {
    // Bloco para carregar a tela de empréstimo
    fetch("emprestimo.jsp") 
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao carregar a tela emprestimo");
            }
            return response.text();
        })
        .then(data => {
            document.getElementById("emprestimo-container").innerHTML = data;

            // Código adicional relacionado a "emprestimo-container"
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

            const inputData = document.getElementById('inicio_parcela');
            if (inputData) {
                inputData.min = dataMinima;
                inputData.max = dataMaximaFormatada;
            }

            const btnEmprestimo = document.querySelector('.btn_emprestimo');
            const fundoEmprestimo = document.querySelector('.fundo_emprestimo');
            const btn_fecharEmprestimo = document.querySelector('.btn_fecharEmprestimo');

            if (btnEmprestimo && fundoEmprestimo) {
                btnEmprestimo.addEventListener('click', () => {
                    fundoEmprestimo.classList.add('ativo');
                });
            }
            if (btn_fecharEmprestimo && fundoEmprestimo) {
                btn_fecharEmprestimo.addEventListener('click', () => {
                    fundoEmprestimo.classList.remove('ativo');
                });
            }
        })
        .catch(error => console.error("Erro ao carregar a tela emprestimo:", error));

    // Bloco para botões abrir/fechar
    const botaoAbrir = document.querySelector(".emprestimo button");
    const botaoFechar = document.querySelector(".emprestimo2 button");

    if (botaoAbrir && botaoFechar) {
        botaoAbrir.addEventListener("click", () => {
            document.querySelector(".emprestimo2").classList.add("ativo");
        });

        botaoFechar.addEventListener("click", () => {
            document.querySelector(".emprestimo2").classList.remove("ativo");
        });
    }
});
