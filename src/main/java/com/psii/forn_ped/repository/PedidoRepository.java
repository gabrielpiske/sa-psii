package com.psii.forn_ped.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psii.forn_ped.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
}
