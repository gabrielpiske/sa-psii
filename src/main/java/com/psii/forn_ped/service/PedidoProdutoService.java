package com.psii.forn_ped.service;

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
}
