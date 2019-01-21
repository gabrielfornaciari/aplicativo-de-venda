package br.com.fornaciari.appVenda.application.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fornaciari.appVenda.application.model.Venda;
import br.com.fornaciari.appVenda.application.repository.VendaRepository;

@Service
public class VendaResources {

	@Autowired
	private VendaRepository vendaRepository;

	public Venda returnVenda(Integer id) {
		return vendaRepository.findById(id).get();
	}
}
