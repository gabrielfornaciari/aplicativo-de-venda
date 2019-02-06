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

import br.com.fornaciari.appVenda.application.model.Compra;
import br.com.fornaciari.appVenda.application.resources.CompraResources;

@RestController
@RequestMapping("/compra")
public class CompraController {
	
	@Autowired
	private CompraResources compraResources;
	
	@GetMapping(path = "/{usuarioId}", produces = "application/json")
	public ResponseEntity<?> getCompras(@PathVariable Integer usuarioId) {
		return compraResources.retornaCompras(usuarioId);
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<?> getCompra(@PathVariable Integer id) {
		return compraResources.retornaCompra(id);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> add(@RequestBody Compra compra) {
		return compraResources.salvarCompra(compra);
	}
	
	@PutMapping(consumes = "application/json")
	public ResponseEntity<?> edit(@RequestBody Compra compra) {
		return compraResources.editarCompra(compra);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		return compraResources.apagarCompra(id);
	}
}
