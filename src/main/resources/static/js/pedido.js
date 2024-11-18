function carregarPedidoParaEdicao(id) {
    fetch('/pedidos/editar/' + id)
        .then(response => response.json())
        .then(data => {
            document.getElementById('pedidoId').value = data.id;
            document.getElementById('data').value = data.data;
            document.getElementById('status').value = data.status;
            document.getElementById('fornecedor').value = data.fornecedor.id;
            //document.getElementById('valorTotal').value = data.valorTotal;

            document.querySelectorAll("input[name='produtoIds']").forEach(input => {
                input.checked = false;
                const quantidadeInput = document.getElementById('quantidade-' + input.value);
                if (quantidadeInput) quantidadeInput.value = '';
            });

            data.pedidoProdutos.forEach(pedidoProduto => {
                const produtoId = pedidoProduto.produtoId;
                const quantidade = pedidoProduto.quantidade;

                const produtoCheckbox = document.querySelector(`input[name='produtoIds'][value='${produtoId}']`);
                const quantidadeInput = document.getElementById('quantidade-' + produtoId);

                if (produtoCheckbox) produtoCheckbox.checked = true;
                if (quantidadeInput) quantidadeInput.value = quantidade; 
                else console.warn(`Input de quantidade não encontrado para produto ID: ${produtoId}`);
            });

            calcularValorTotal();

            document.getElementById('pedidoModalLabel').textContent = 'Editar Pedido';
        })
        .catch(error => console.error("Erro ao carregar pedido:", error));
}

function abrirModalParaNovoPedido() {
    document.getElementById('pedidoForm').reset();
    document.getElementById('pedidoId').value = '';
    document.getElementById('pedidoModalLabel').textContent = 'Adicionar Pedido';
}

function calcularValorTotal(){
    let valorTotal = 0;

    document.querySelectorAll("input[name='produtoIds']").forEach(input => {
        if(input.checked){
            const produtoId = input.value;
            const quantidadeInput = document.getElementById('quantidade-' + produtoId);
            const precoInput = document.getElementById('preco-' + produtoId);

            const quantidade = parseFloat(quantidadeInput.value) || 0;
            const preco = parseFloat(precoInput.value) || 0;

            valorTotal += quantidade * preco;
        }
    });
    document.getElementById('valorTotalDisplay').value = `R$ ${valorTotal.toFixed(2)}`;
}
// Adicionar eventos para recalcular o valor total
document.querySelectorAll("input[name='produtoIds']").forEach(input => {
    input.addEventListener('change', calcularValorTotal);
});

document.querySelectorAll("input[name='quantidades']").forEach(input => {
    input.addEventListener('input', calcularValorTotal);
});