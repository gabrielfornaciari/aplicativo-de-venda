package br.com.fornaciari.appVenda.application.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fornaciari.appVenda.application.model.Produto;
import br.com.fornaciari.appVenda.application.repository.ProdutoRepository;

@Service
public class ProdutoResources {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto returnProduto(Integer id) {
		return produtoRepository.findById(id).get();
	}
}
