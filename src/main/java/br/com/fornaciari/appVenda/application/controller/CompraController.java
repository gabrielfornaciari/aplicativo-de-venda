package br.com.fornaciari.appVenda.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fornaciari.appVenda.application.model.Compra;
import br.com.fornaciari.appVenda.application.repository.CompraRepository;
import br.com.fornaciari.appVenda.application.resources.CompraResources;

@RestController
@RequestMapping("/compra")
public class CompraController {
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private CompraResources compraResources;
	
	@GetMapping(produces = "application/json")
	public @ResponseBody List<Compra> buscaCompras() {
		return compraRepository.findAll();
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public @ResponseBody Compra getCompra(@PathVariable Integer id) {
		return compraResources.returnCompra(id);
	}
	
	@PostMapping(consumes = "application/json")
	public Compra salvaCompra(@RequestBody Compra compra) {
		return compraRepository.save(compra);
	}
	
	@DeleteMapping("/{id}")
	public Compra apagaCompra(@PathVariable Integer id) {
		Compra compra = compraResources.returnCompra(id);
		compraRepository.delete(compra);
		return compra;
	}
}
