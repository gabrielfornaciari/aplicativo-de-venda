package br.com.fornaciari.appVenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fornaciari.appVenda.model.Produto;

public interface VendaRepository extends JpaRepository<Produto, Integer>{

}
