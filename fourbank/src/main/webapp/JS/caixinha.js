document.addEventListener("DOMContentLoaded", function () {
    fetch("caixinha.jsp") 
        .then((response) => {
            if (!response.ok) {
                throw new Error("Erro ao carregar a tela caixinha");
            }
            return response.text();
        })
        .then((data) => {
            document.getElementById("caixinha-container").innerHTML = data;

            // Adicionar evento de clique para mover o fundo do empréstimo (dentro do `then`)
            const btncaixinha = document.querySelector('.btn_caixinha');
            const fundocaixinha = document.querySelector('.fundo_caixinha');
            const btn_fecharcaixinha = document.querySelector('.btn_fecharcaixinha');

            if (btncaixinha && fundocaixinha) {
                btncaixinha.addEventListener('click', function() {
                    fundocaixinha.classList.add('ativo');
                }); 
            }
            if (btn_fecharcaixinha && fundocaixinha) {
                btn_fecharcaixinha.addEventListener('click', function() {
                    fundocaixinha.classList.remove('ativo');
                });
            }
        })
        .catch((error) => console.error("Erro ao carregar a tela caixinha:", error));
});
