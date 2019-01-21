package br.com.fornaciari.appVenda.application.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fornaciari.appVenda.application.model.Compra;
import br.com.fornaciari.appVenda.application.repository.CompraRepository;

@Service
public class CompraResources {

	@Autowired
	private CompraRepository compraRepository;

	public Compra retornaCompra(Integer id) {
		return compraRepository.findById(id).get();
	}

	public List<Compra> retornaCompras(){
		return compraRepository.findAll();
	}
	
	public Compra salvarOuEditarCompra(Compra compra) {
		return compraRepository.save(compra);
	}
	
	public Compra apagarCompra(Integer id) {
		Compra compra = retornaCompra(id);
		compraRepository.delete(compra);
		return compra;
	}
}
