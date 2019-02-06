package br.com.fornaciari.appVenda.application.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fornaciari.appVenda.application.model.Cliente;
import br.com.fornaciari.appVenda.application.repository.ClienteRepository;
import br.com.fornaciari.appVenda.application.util.Translator;

@Service
public class ClienteResources {

	@Autowired
	private ClienteRepository clienteRepository;

	public ResponseEntity<Cliente> retornaCliente(Integer id) {
		Optional<Cliente> cliente = buscaCliente(id);
		if (!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente.get());
	}
	
	public ResponseEntity<List<Cliente>> retornaClientes(Integer usuarioId){
		List<Cliente> clientes = clienteRepository.findByUsuario_id(usuarioId);
		if (clientes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clientes);
	}
	
	public ResponseEntity<?> salvarCliente(Cliente cliente) {
		if (cliente != null && cliente.getId() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Translator.toLocale("erro.salvar.nao.editar"));
		}
		try {
			cliente = clienteRepository.save(cliente);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idUsuario}/{id}").buildAndExpand(cliente.getUsuario().getId(), cliente.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Translator.toLocale("erro.interno"));
		}
	}
	
	public ResponseEntity<?> editarCliente(Cliente cliente) {
		if (cliente != null && cliente.getId() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Translator.toLocale("erro.editar.nao.salvar"));
		}
		try {
			cliente = clienteRepository.save(cliente);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Translator.toLocale("erro.interno"));
		}
	}
	
	public ResponseEntity<Cliente> apagarCliente(Integer id) {
		Optional<Cliente> cliente = buscaCliente(id);
		if (!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		try {
			clienteRepository.delete(cliente.get());
			return ResponseEntity.ok(cliente.get());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	private Optional<Cliente> buscaCliente(Integer id){
		return clienteRepository.findById(id);
	}
}
