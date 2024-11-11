package com.psii.forn_ped.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psii.forn_ped.model.Pedido;
import com.psii.forn_ped.model.PedidoProduto;
import com.psii.forn_ped.repository.PedidoProdutoRepository;
import com.psii.forn_ped.repository.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void savePedidoProduto(PedidoProduto pedidoProduto) {
        pedidoProdutoRepository.save(pedidoProduto);
    }

    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }
}
