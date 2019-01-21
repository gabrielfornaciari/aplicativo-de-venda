package br.com.fornaciari.appVenda.application.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fornaciari.appVenda.application.model.Cliente;
import br.com.fornaciari.appVenda.application.repository.ClienteRepository;

@Service
public class ClienteResources {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente retornaCliente(Integer id) {
		return clienteRepository.findById(id).get();
	}
	
	public List<Cliente> retornaClientes(){
		return clienteRepository.findAll();
	}
	
	public Cliente salvarOuEditarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente apagarCliente(Integer id) {
		Cliente cliente = retornaCliente(id);
		clienteRepository.delete(cliente);
		return cliente;
	}
}
