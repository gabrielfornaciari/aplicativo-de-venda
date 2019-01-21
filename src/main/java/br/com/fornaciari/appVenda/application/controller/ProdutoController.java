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

import br.com.fornaciari.appVenda.application.model.Produto;
import br.com.fornaciari.appVenda.application.resources.ProdutoResources;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoResources produtoResources;
	
	@GetMapping(produces = "application/json")
	public @ResponseBody List<Produto> getProdutos() {
		return produtoResources.retornaProdutos();
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public @ResponseBody Produto getProduto(@PathVariable Integer id) {
		return produtoResources.retornaProduto(id);
	}
	
	@PostMapping(consumes = "application/json")
	public Produto addAndEdit(@RequestBody Produto produto) {
		return produtoResources.salvarOuEditarProduto(produto);
	}
	
	@DeleteMapping("/{id}")
	public Produto delete(@PathVariable Integer id) {
		return produtoResources.apagarProduto(id);
	}
}
