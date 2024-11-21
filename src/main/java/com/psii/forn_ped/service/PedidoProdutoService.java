package com.psii.forn_ped.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psii.forn_ped.model.PedidoProduto;
import com.psii.forn_ped.repository.PedidoProdutoRepository;

@Service
public class PedidoProdutoService {
    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    public void save(PedidoProduto pedidoProduto) {
        pedidoProdutoRepository.save(pedidoProduto);
    }

    public List<PedidoProduto> findAll() {
        return pedidoProdutoRepository.findAll();
    }

    public Optional<PedidoProduto> findById(Long id) {
        return pedidoProdutoRepository.findById(id);
    }

    public void deleteById(Long id) {
        pedidoProdutoRepository.deleteById(id);
    }
}
