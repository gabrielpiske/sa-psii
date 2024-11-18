function openEditModal(id) {
    fetch(`/fornecedores/editar/${id}`)
      .then((response) => response.json())
      .then((data) => {
        document.getElementById(
          "edit-form"
        ).action = `/fornecedores/atualizar/${data.id}`;
        document.getElementById("edit-empresa").value = data.empresa;
        document.getElementById("edit-contato").value = data.contato;
        document.getElementById("edit-telefone").value = data.telefone;
        document.getElementById("edit-email").value = data.email;
  
        new bootstrap.Modal(document.getElementById("editModal")).show();
      })
      .catch((error) => console.error("Erro ao buscar fornecedor:", error));
}