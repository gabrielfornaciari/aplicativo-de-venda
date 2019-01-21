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
import br.com.fornaciari.appVenda.application.repository.ProdutoRepository;
import br.com.fornaciari.appVenda.application.resources.ProdutoResources;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoResources produtoResources;
	
	@GetMapping(produces = "application/json")
	public @ResponseBody List<Produto> buscaProdutos() {
		return produtoRepository.findAll();
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public @ResponseBody Produto getProduto(@PathVariable Integer id) {
		return produtoResources.returnProduto(id);
	}
	
	@PostMapping(consumes = "application/json")
	public Produto salvaProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@DeleteMapping("/{id}")
	public Produto apagaProduto(@PathVariable Integer id) {
		Produto produto = produtoResources.returnProduto(id);
		produtoRepository.delete(produto);
		return produto;
	}
}
