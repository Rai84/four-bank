function pagarParcela() {
    // Confirmar o pagamento com o usuário
    if (confirm("Você deseja pagar esta parcela?")) {
        // Enviar solicitação ao backend
        fetch("/pagarParcela", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ action: "pagarParcela" })
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("Parcela paga com sucesso!");
                // Atualizar a página ou os dados dinamicamente
                location.reload();
            } else {
                alert("Erro ao pagar parcela: " + data.message);
            }
        })
        .catch(error => console.error("Erro na solicitação:", error));
    }
}
