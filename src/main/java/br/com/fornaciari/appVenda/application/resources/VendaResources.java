package br.com.fornaciari.appVenda.application.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fornaciari.appVenda.application.model.Venda;
import br.com.fornaciari.appVenda.application.repository.VendaRepository;
import br.com.fornaciari.appVenda.application.util.Translator;

@Service
public class VendaResources {

	@Autowired
	private VendaRepository vendaRepository;

	public ResponseEntity<Venda> retornaVenda(Integer id) {
		Optional<Venda> venda = buscaVenda(id);
		if (!venda.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(venda.get());
	}
	
	public ResponseEntity<List<Venda>> retornaVendas(Integer usuarioId){
		List<Venda> vendas = vendaRepository.findByUsuario_id(usuarioId);
		if (vendas.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(vendas);
	}
	
	public ResponseEntity<?> salvarVenda(Venda venda) {
		if (venda != null && venda.getId() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Translator.toLocale("erro.salvar.nao.editar"));
		}
		try {
			venda = vendaRepository.save(venda);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idUsuario}/{id}").buildAndExpand(venda.getUsuario().getId(), venda.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Translator.toLocale("erro.interno"));
		}
	}
	
	public ResponseEntity<?> editarVenda(Venda venda) {
		if (venda != null && venda.getId() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Translator.toLocale("erro.editar.nao.salvar"));
		}
		try {
			venda = vendaRepository.save(venda);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Translator.toLocale("erro.interno"));
		}
	}
	
	public ResponseEntity<Venda> apagarVenda(Integer id) {
		Optional<Venda> venda = buscaVenda(id);
		if (!venda.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		try {
			vendaRepository.delete(venda.get());
			return ResponseEntity.ok(venda.get());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	private Optional<Venda> buscaVenda(Integer id){
		return vendaRepository.findById(id);
	}
}
