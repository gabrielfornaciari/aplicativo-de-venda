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

import br.com.fornaciari.appVenda.application.model.Produto;
import br.com.fornaciari.appVenda.application.resources.ProdutoResources;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoResources produtoResources;
	
	@GetMapping(path = "/{usuarioId}", produces = "application/json")
	public ResponseEntity<?> getProdutos(@PathVariable Integer usuarioId) {
		return produtoResources.retornaProdutos(usuarioId);
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<?> getProduto(@PathVariable Integer id) {
		return produtoResources.retornaProduto(id);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> add(@RequestBody Produto produto) {
		return produtoResources.salvarProduto(produto);
	}
	
	@PutMapping(consumes = "application/json")
	public ResponseEntity<?> edit(@RequestBody Produto produto) {
		return produtoResources.editarProduto(produto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		return produtoResources.apagarProduto(id);
	}
}
