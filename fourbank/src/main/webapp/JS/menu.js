document.addEventListener("DOMContentLoaded", function () {
    // Carrega o menu dinamicamente
    fetch("menu.html")
        .then((response) => {
            if (!response.ok) {
                throw new Error("Erro ao carregar o menu");
            }
            return response.text();
        })
        .then((data) => {
            // Insere o conteúdo do menu no contêiner
            document.getElementById("menu-container").innerHTML = data;

            // Após carregar o menu, aplica o destaque ao botão ativo
            const currentPath = window.location.pathname;
            const menuItems = document.querySelectorAll("#menu-container a");

            menuItems.forEach(item => {
                if (currentPath.includes(item.getAttribute("href"))) {
                    item.classList.add("active"); // Adiciona a classe 'active'
                }
            });
        })
        .catch((error) => console.error("Erro ao carregar o menu:", error));
});
