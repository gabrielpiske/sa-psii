package com.psii.forn_ped.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.psii.forn_ped.model.Produto;
import com.psii.forn_ped.service.ProdutoService;
import com.psii.forn_ped.service.FornecedorService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public String listProdutos(Model model){
        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos);
        model.addAttribute("produto", new Produto());
        model.addAttribute("fornecedores", fornecedorService.findAll());
        model.addAttribute("abrirModal", false);
        return "produto";
    }

    @PostMapping("/salvar")
    public String salvarProduto(Produto produto) {
        produtoService.save(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<Produto> editarProduto(@PathVariable("id") Long id) {
        Optional<Produto> produtoOpt = produtoService.findById(id);
        return produtoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarProduto(@PathVariable("id") Long id, Produto produto) {
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
