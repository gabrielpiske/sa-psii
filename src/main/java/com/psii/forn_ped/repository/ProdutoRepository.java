package com.psii.forn_ped.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psii.forn_ped.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
