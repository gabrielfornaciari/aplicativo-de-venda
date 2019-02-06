package br.com.fornaciari.appVenda.application.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fornaciari.appVenda.application.model.Produto;
import br.com.fornaciari.appVenda.application.repository.ProdutoRepository;
import br.com.fornaciari.appVenda.application.util.Translator;

@Service
public class ProdutoResources {

	@Autowired
	private ProdutoRepository produtoRepository;

	public ResponseEntity<Produto> retornaProduto(Integer id) {
		Optional<Produto> produto = buscaProduto(id);
		if (!produto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produto.get());
	}
	
	public ResponseEntity<List<Produto>> retornaProdutos(Integer usuarioId){
		List<Produto> produtos = produtoRepository.findByUsuario_id(usuarioId);
		if (produtos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produtos);
	}
	
	public ResponseEntity<?> salvarProduto(Produto produto) {
		if (produto != null && produto.getId() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Translator.toLocale("erro.salvar.nao.editar"));
		}
		try {
			produto = produtoRepository.save(produto);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idUsuario}/{id}").buildAndExpand(produto.getUsuario().getId(), produto.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Translator.toLocale("erro.interno"));
		}
	}
	
	public ResponseEntity<?> editarProduto(Produto produto) {
		if (produto != null && produto.getId() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Translator.toLocale("erro.editar.nao.salvar"));
		}
		try {
			produto = produtoRepository.save(produto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Translator.toLocale("erro.interno"));
		}
	}
	
	public ResponseEntity<Produto> apagarProduto(Integer id) {
		Optional<Produto> produto = buscaProduto(id);
		if (!produto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		try {
			produtoRepository.delete(produto.get());
			return ResponseEntity.ok(produto.get());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	private Optional<Produto> buscaProduto(Integer id){
		return produtoRepository.findById(id);
	}
}
