package br.com.fornaciari.appVenda.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fornaciari.appVenda.application.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Integer>{

	List<Compra> findByUsuario_id(Integer usuarioId);
	
}
