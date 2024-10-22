document.addEventListener("DOMContentLoaded", function () {
    fetch("ajuda.html")
        .then((response) => {
            if (!response.ok) {
                throw new Error("Erro ao carregar o btn ajuda");
            }
            return response.text();
        })
        .then((data) => {
            document.getElementById("ajuda-container").innerHTML = data;
        })
        .catch((error) => console.error("Erro ao carregar o btn ajuda:", error));
});
