package br.com.fornaciari.appVenda.application.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fornaciari.appVenda.application.model.Venda;
import br.com.fornaciari.appVenda.application.repository.VendaRepository;

@Service
public class VendaResources {

	@Autowired
	private VendaRepository vendaRepository;

	public Venda retornaVenda(Integer id) {
		return vendaRepository.findById(id).get();
	}
	
	public List<Venda> retornaVendas(){
		return vendaRepository.findAll();
	}
	
	public Venda salvarOuEditarVenda(Venda venda) {
		return vendaRepository.save(venda);
	}
	
	public Venda apagarVenda(Integer id) {
		Venda venda = retornaVenda(id);
		vendaRepository.delete(venda);
		return venda;
	}
}
