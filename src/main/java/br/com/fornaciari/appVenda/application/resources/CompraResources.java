package br.com.fornaciari.appVenda.application.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fornaciari.appVenda.application.model.Compra;
import br.com.fornaciari.appVenda.application.repository.CompraRepository;
import br.com.fornaciari.appVenda.application.util.Translator;

@Service
public class CompraResources {

	@Autowired
	private CompraRepository compraRepository;

	public ResponseEntity<Compra> retornaCompra(Integer id) {
		Optional<Compra> compra = buscaCompra(id);
		if (!compra.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(compra.get());
	}

	public ResponseEntity<List<Compra>> retornaCompras(Integer usuarioId){
		List<Compra> compras = compraRepository.findByUsuario_id(usuarioId);
		if (compras.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(compras);
	}
	
	public ResponseEntity<?> salvarCompra(Compra compra) {
		if (compra != null && compra.getId() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Translator.toLocale("erro.salvar.nao.editar"));
		}
		try {
			compra = compraRepository.save(compra);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idUsuario}/{id}").buildAndExpand(compra.getUsuario().getId(), compra.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Translator.toLocale("erro.interno"));
		}
	}
	
	public ResponseEntity<?> editarCompra(Compra compra) {
		if (compra != null && compra.getId() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Translator.toLocale("erro.editar.nao.salvar"));
		}
		try {
			compra = compraRepository.save(compra);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Translator.toLocale("erro.interno"));
		}
	}
	
	public ResponseEntity<Compra> apagarCompra(Integer id) {
		Optional<Compra> compra = buscaCompra(id);
		if (!compra.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		try {
			compraRepository.delete(compra.get());
			return ResponseEntity.ok(compra.get());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	private Optional<Compra> buscaCompra(Integer id){
		return compraRepository.findById(id);
	}
}
