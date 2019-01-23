package br.com.fornaciari.appVenda.application.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fornaciari.appVenda.application.model.Usuario;
import br.com.fornaciari.appVenda.application.repository.UsuarioRepository;

@Service
public class UsuarioResources {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario retornaUsuario(Integer id) {
		return usuarioRepository.findById(id).get();
	}
	
	public List<Usuario> retornaUsuarios(){
		return usuarioRepository.findAll();
	}
	
	public Usuario salvarOuEditarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario apagarUsuario(Integer id) {
		Usuario usuario = retornaUsuario(id);
		usuarioRepository.delete(usuario);
		return usuario;
	}
}
