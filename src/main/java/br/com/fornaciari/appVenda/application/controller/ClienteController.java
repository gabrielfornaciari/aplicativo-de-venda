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

import br.com.fornaciari.appVenda.application.model.Cliente;
import br.com.fornaciari.appVenda.application.resources.ClienteResources;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteResources clienteResources;
	
	@GetMapping(path = "/{usuarioId}", produces = "application/json")
	public ResponseEntity<?> getClientes(@PathVariable Integer usuarioId) {
		return clienteResources.retornaClientes(usuarioId);
	}
	
	@GetMapping(path = "/{usuarioId}/{id}", produces = "application/json")
	public ResponseEntity<?> getCliente(@PathVariable Integer id) {
		return clienteResources.retornaCliente(id);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> add(@RequestBody Cliente cliente) {
		return clienteResources.salvarCliente(cliente);
	}
	
	@PutMapping(consumes = "application/json")
	public ResponseEntity<?> edit(@RequestBody Cliente cliente) {
		return clienteResources.editarCliente(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		return clienteResources.apagarCliente(id);
	}
}
