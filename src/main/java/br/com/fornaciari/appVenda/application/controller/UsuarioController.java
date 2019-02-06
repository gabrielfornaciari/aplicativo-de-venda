package br.com.fornaciari.appVenda.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fornaciari.appVenda.application.model.Usuario;
import br.com.fornaciari.appVenda.application.resources.UsuarioResources;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioResources usuarioResources;
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<?> getUsuarios() {
		return usuarioResources.retornaUsuarios();
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<?> getUsuario(@PathVariable Integer id) {
		return usuarioResources.retornaUsuario(id);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> add(@RequestBody Usuario usuario) {
		return usuarioResources.salvarUsuario(usuario);
	}
	
	@PutMapping(consumes = "application/json")
	public ResponseEntity<?> edit(@RequestBody Usuario usuario) {
		return usuarioResources.editarUsuario(usuario);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		return usuarioResources.apagarUsuario(id);
	}
}
