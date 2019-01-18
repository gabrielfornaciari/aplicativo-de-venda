package br.com.fornaciari.appVenda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fornaciari.appVenda.model.Cliente;
import br.com.fornaciari.appVenda.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping(produces = "application/json")
	public @ResponseBody List<Cliente> getClientes() {
		return clienteRepository.findAll();
	}
	
	@GetMapping(path = "/buscaCliente/{id}", produces = "application/json")
	public @ResponseBody Cliente getCliente(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}
	
	@PostMapping()
	public Cliente salvaCliente(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@DeleteMapping()
	public Cliente apagaCliente(@RequestBody Cliente cliente) {
		clienteRepository.delete(cliente);
		return cliente;
	}
}
