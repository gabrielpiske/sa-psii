package com.psii.forn_ped.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psii.forn_ped.model.Produto;
import com.psii.forn_ped.service.FornecedorService;
import com.psii.forn_ped.service.ProdutoService;



@Controller
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos);
        model.addAttribute("produto", new Produto());
        model.addAttribute("fornecedores", fornecedorService.findAll());
        return "produto";
    }

    @PostMapping
    public String adicionarProduto(Produto produto) {
        produtoService.save(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable("id") Long id, Model model) {
        Produto produto = produtoService.findById(id).orElse(null);
        model.addAttribute("produto", produto);
        model.addAttribute("podutos", produtoService.findAll());
        model.addAttribute("fornecedores", fornecedorService.findAll());
        return "produto";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarPedido(@PathVariable("id") Long id, Produto produto) {
        produto.setId(id);
        produtoService.save(produto);
        return "redirect:/produtos";
    }
    
    @GetMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable("id") Long id) {
        produtoService.deleteById(id);
        return "redirect:/produtos";
    }
    
}
