package br.com.fornaciari.appVenda.application.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fornaciari.appVenda.application.model.Cliente;
import br.com.fornaciari.appVenda.application.repository.ClienteRepository;

@Service
public class ClienteResources {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente returnCliente(Integer id) {
		return clienteRepository.findById(id).get();
	}
}
