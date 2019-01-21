package br.com.fornaciari.appVenda.application.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fornaciari.appVenda.application.model.Compra;
import br.com.fornaciari.appVenda.application.repository.CompraRepository;

@Service
public class CompraResources {

	@Autowired
	private CompraRepository compraRepository;

	public Compra returnCompra(Integer id) {
		return compraRepository.findById(id).get();
	}
}
