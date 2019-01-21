package br.com.fornaciari.appVenda.application.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fornaciari.appVenda.application.model.Produto;
import br.com.fornaciari.appVenda.application.repository.ProdutoRepository;

@Service
public class ProdutoResources {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto retornaProduto(Integer id) {
		return produtoRepository.findById(id).get();
	}
	
	public List<Produto> retornaProdutos(){
		return produtoRepository.findAll();
	}
	
	public Produto salvarOuEditarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto apagarProduto(Integer id) {
		Produto produto = retornaProduto(id);
		produtoRepository.delete(produto);
		return produto;
	}
}
