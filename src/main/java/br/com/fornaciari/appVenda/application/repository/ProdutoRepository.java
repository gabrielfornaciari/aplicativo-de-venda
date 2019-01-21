package br.com.fornaciari.appVenda.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fornaciari.appVenda.application.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
