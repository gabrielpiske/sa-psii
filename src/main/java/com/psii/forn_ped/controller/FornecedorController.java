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

import com.psii.forn_ped.model.Fornecedor;
import com.psii.forn_ped.service.FornecedorService;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {
    
    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public String listFornecedores(Model model){
        List<Fornecedor> fornecedores = fornecedorService.findAll();
        model.addAttribute("fornecedores", fornecedores);
        model.addAttribute("fornecedor", new Fornecedor());
        model.addAttribute("abrirModal", false);
        return "fornecedor";
    }

    @PostMapping("/salvar")
    public String salvarFornecedor(Fornecedor fornecedor) {
        fornecedorService.save(fornecedor);
        return "redirect:/fornecedores";
    }

    @GetMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<Fornecedor> editarFornecedor(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedorOpt = fornecedorService.findById(id);
        return fornecedorOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }   

    @PostMapping("/atualizar/{id}")
    public String atualizarFornecedor(@PathVariable("id") Long id, Fornecedor fornecedor){
        fornecedor.setId(id);
        fornecedorService.save(fornecedor);
        return "redirect:/fornecedores";
    }

    @GetMapping("/deletar/{id}")
    public String deletarFornecedor(@PathVariable("id") Long id) {
        fornecedorService.deleteById(id);
        return "redirect:/fornecedores";
    }
}
