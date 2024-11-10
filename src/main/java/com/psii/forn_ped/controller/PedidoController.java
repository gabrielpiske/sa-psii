package com.psii.forn_ped.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.psii.forn_ped.model.Pedido;
import com.psii.forn_ped.model.PedidoProduto;
import com.psii.forn_ped.model.Produto;
import com.psii.forn_ped.service.FornecedorService;
import com.psii.forn_ped.service.PedidoService;
import com.psii.forn_ped.service.ProdutoService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public String listPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.findAll();
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("fornecedores", fornecedorService.findAll());
        model.addAttribute("produtos", produtoService.findAll());
        model.addAttribute("abrirModal", false);
        return "pedido";
    }

    @PostMapping("/salvar")
    public String salvarPedido(Pedido pedido,
            @RequestParam(value = "produtoIds", required = false) Long[] produtoIds,
            @RequestParam(value = "quantidades", required = false) Integer[] quantidades) {

        // Lista para armazenar as associações de PedidoProduto
        List<PedidoProduto> pedidoProdutos = new ArrayList<>();

        // Verifica se ambos os arrays não são nulos e possuem o mesmo tamanho
        if (produtoIds != null && quantidades != null && produtoIds.length == quantidades.length) {
            // Criação das associações de PedidoProduto
            for (int i = 0; i < produtoIds.length; i++) {
                Produto produto = produtoService.findById(produtoIds[i]).orElse(null);
                if (produto != null) {
                    PedidoProduto pedidoProduto = new PedidoProduto();
                    pedidoProduto.setPedido(pedido);
                    pedidoProduto.setProduto(produto);
                    pedidoProduto.setQuantidade(quantidades[i]);
                    pedidoProdutos.add(pedidoProduto);
                }
            }
        } else {
            // Caso os arrays sejam nulos ou tenham tamanhos diferentes, você pode decidir o
            // que fazer.
            // Aqui, apenas retornaremos para evitar salvar um pedido incompleto.
            return "redirect:/pedidos?error=invalidData";
        }

        pedido.setPedidoProdutos(pedidoProdutos);
        pedidoService.save(pedido);

        return "redirect:/pedidos";
    }

    @GetMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<Pedido> editarPedido(@PathVariable("id") Long id) {
        Optional<Pedido> pedidoOpt = pedidoService.findById(id);
        return pedidoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarPedido(@PathVariable("id") Long id, Pedido pedido) {
        pedido.setId(id);
        pedidoService.save(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/deletar/{id}")
    public String deletarPedido(@PathVariable("id") Long id) {
        pedidoService.deleteById(id);
        return "redirect:/pedidos";
    }
}
