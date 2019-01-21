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

import br.com.fornaciari.appVenda.application.model.Venda;
import br.com.fornaciari.appVenda.application.resources.VendaResources;

@RestController
@RequestMapping("/venda")
public class VendaController {
	
	@Autowired
	private VendaResources vendaResources;
	
	@GetMapping(produces = "application/json")
	public @ResponseBody List<Venda> getVendas() {
		return vendaResources.retornaVendas();
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public @ResponseBody Venda getVenda(@PathVariable Integer id) {
		return vendaResources.retornaVenda(id);
	}
	
	@PostMapping(consumes = "application/json")
	public Venda addAndEdit(@RequestBody Venda venda) {
		return vendaResources.salvarOuEditarVenda(venda);
	}
	
	@DeleteMapping("/{id}")
	public Venda delete(@PathVariable Integer id) {
		return vendaResources.apagarVenda(id);
	}
}
