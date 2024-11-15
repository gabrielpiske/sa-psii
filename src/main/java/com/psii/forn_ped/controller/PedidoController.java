package com.psii.forn_ped.controller;

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
import org.springframework.web.bind.annotation.PostMapping;

import com.psii.forn_ped.model.Pedido;
import com.psii.forn_ped.model.PedidoProduto;
import com.psii.forn_ped.model.Produto;
import com.psii.forn_ped.service.FornecedorService;
import com.psii.forn_ped.service.PedidoProdutoService;
import com.psii.forn_ped.service.PedidoService;
import com.psii.forn_ped.service.ProdutoService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PedidoProdutoService pedidoProdutoService;

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
            @RequestParam("produtoIds") Long[] produtoIds,
            @RequestParam("quantidades") Integer[] quantidades,
            Model model) {
        // Verificar se pelo menos um produto foi selecionado
        if (produtoIds.length == 0) {
            model.addAttribute("erro", "Você deve selecionar pelo menos um produto.");
            return listPedidos(model);
        }
        
        // Salvar pedido
        pedidoService.save(pedido);
        
        // Salvar relacionamentos de PedidoProduto
        pedido.getPedidoProdutos().clear();
        for (int i = 0; i < produtoIds.length; i++) {
            Produto produto = produtoService.findById(produtoIds[i]).orElseThrow();
            //PedidoProduto pedidoProduto = new PedidoProduto(pedido, produto, quantidades[i]);
            PedidoProduto pedidoProduto = new PedidoProduto();
            pedidoProduto.setPedido(pedido);
            pedidoProduto.setProduto(produto);
            pedidoProduto.setQuantidade(quantidades[i]);
            pedidoProdutoService.save(pedidoProduto);
        }
        
        return "redirect:/pedidos";
    }

    @GetMapping("/editar/{id}")
    @ResponseBody
    public Pedido editarPedido(@PathVariable("id") Long id) {
        return pedidoService.findById(id).orElseThrow();
    }

    @GetMapping("/deletar/{id}")
    public String deletarPedido(@PathVariable("id") Long id) {
        pedidoService.deleteById(id);
        return "redirect:/pedidos";
    }
}
