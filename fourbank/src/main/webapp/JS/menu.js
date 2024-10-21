document.addEventListener("DOMContentLoaded", function () {
    fetch("menu.html") // Certifique-se de que este caminho está correto
        .then((response) => {
            if (!response.ok) {
                throw new Error("Erro ao carregar o menu");
            }
            return response.text();
        })
        .then((data) => {
            document.getElementById("menu-container").innerHTML = data;
        })
        .catch((error) => console.error("Erro ao carregar o menu:", error));
});
