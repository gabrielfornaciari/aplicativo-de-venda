package br.com.fornaciari.appVenda.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fornaciari.appVenda.application.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Integer>{

}
