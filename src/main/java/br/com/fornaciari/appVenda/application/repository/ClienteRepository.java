package br.com.fornaciari.appVenda.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fornaciari.appVenda.application.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	List<Cliente> findByUsuario_id(Integer usuarioId);
}
