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

import br.com.fornaciari.appVenda.application.model.Venda;
import br.com.fornaciari.appVenda.application.resources.VendaResources;

@RestController
@RequestMapping("/venda")
public class VendaController {
	
	@Autowired
	private VendaResources vendaResources;
	
	@GetMapping(path = "/{usuarioId}", produces = "application/json")
	public ResponseEntity<?> getVendas(@PathVariable Integer usuarioId) {
		return vendaResources.retornaVendas(usuarioId);
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<?> getVenda(@PathVariable Integer id) {
		return vendaResources.retornaVenda(id);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> add(@RequestBody Venda venda) {
		return vendaResources.salvarVenda(venda);
	}
	
	@PutMapping(consumes = "application/json")
	public ResponseEntity<?> edit(@RequestBody Venda venda) {
		return vendaResources.editarVenda(venda);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		return vendaResources.apagarVenda(id);
	}
}
