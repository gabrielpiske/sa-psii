function populateEditModal(id) {
    fetch(`/produtos/editar/${id}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById("editForm").action = `/produtos/atualizar/${data.id}`;
            
            document.getElementById("editDescricao").value = data.descricao;
            document.getElementById("editPreco").value = data.preco;
            document.getElementById("editCategoria").value = data.categoria;
            document.getElementById("editDataValidade").value = data.dataValidade;
            document.getElementById("editFornecedor").value = data.fornecedor.id;
            

            new bootstrap.Modal(document.getElementById("editProdutoModal")).show();
        })
        .catch(error => console.error("Erro ao buscar produto:", error));
}