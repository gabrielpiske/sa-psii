function carregarPedidoParaEdicao(id) {
    fetch('/pedidos/editar/' + id)
        .then(response => response.json())
        .then(data => {
            document.getElementById('pedidoId').value = data.id;
            document.getElementById('data').value = data.data;
            document.getElementById('status').value = data.status;
            document.getElementById('fornecedor').value = data.fornecedor.id;
            document.getElementById('valorTotal').value = data.valorTotal;

            document.querySelectorAll("input[name='produtoIds']").forEach(input => {
                const produtoId = input.value;
                const produtoSelecionado = data.pedidoProdutos.find(p => p.produto.id == produtoId);

                // define se o produto ta selecionado e sua quantidade
                input.checked = !!produtoSelecionado;
                document.getElementById('quantidade-' + produtoId).value = produtoSelecionado ? produtoSelecionado.quantidade : '';
            });

            document.getElementById('pedidoModalLabel').textContent = 'Editar Pedido';
        })
        .catch(error => console.error("Erro ao carregar pedido:", error));
}

function abrirModalParaNovoPedido() {
    document.getElementById('pedidoForm').reset();
    document.getElementById('pedidoId').value = '';
    document.getElementById('pedidoModalLabel').textContent = 'Adicionar Pedido';
}