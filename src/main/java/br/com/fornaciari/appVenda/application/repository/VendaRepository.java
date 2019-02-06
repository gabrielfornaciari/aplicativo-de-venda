package br.com.fornaciari.appVenda.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fornaciari.appVenda.application.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Integer>{

	List<Venda> findByUsuario_id(Integer usuarioId);
	
}
