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

import br.com.fornaciari.appVenda.application.model.Cliente;
import br.com.fornaciari.appVenda.application.repository.ClienteRepository;
import br.com.fornaciari.appVenda.application.resources.ClienteResources;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteResources clienteResources;
	
	@GetMapping(produces = "application/json")
	public @ResponseBody List<Cliente> buscaClientes() {
		return clienteRepository.findAll();
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public @ResponseBody Cliente getCliente(@PathVariable Integer id) {
		return clienteResources.returnCliente(id);
	}
	
	@PostMapping(consumes = "application/json")
	public Cliente salvaCliente(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@DeleteMapping("/{id}")
	public Cliente apagaCliente(@PathVariable Integer id) {
		Cliente cliente = clienteResources.returnCliente(id);
		clienteRepository.delete(cliente);
		return cliente;
	}
}
