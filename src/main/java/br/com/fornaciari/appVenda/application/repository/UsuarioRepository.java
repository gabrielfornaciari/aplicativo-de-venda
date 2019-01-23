package br.com.fornaciari.appVenda.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fornaciari.appVenda.application.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
