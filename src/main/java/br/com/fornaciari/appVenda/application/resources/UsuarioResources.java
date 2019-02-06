package br.com.fornaciari.appVenda.application.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fornaciari.appVenda.application.model.Usuario;
import br.com.fornaciari.appVenda.application.repository.UsuarioRepository;
import br.com.fornaciari.appVenda.application.util.Translator;

@Service
public class UsuarioResources {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public ResponseEntity<Usuario> retornaUsuario(Integer id) {
		Optional<Usuario> usuario = buscaUsuario(id);
		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario.get());
	}
	
	public ResponseEntity<List<Usuario>> retornaUsuarios(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		if (usuarios.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuarios);
	}
	
	public ResponseEntity<?> salvarUsuario(Usuario usuario) {
		if (usuario != null && usuario.getId() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Translator.toLocale("erro.salvar.nao.editar"));
		}
		try {
			usuario = usuarioRepository.save(usuario);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Translator.toLocale("erro.interno"));
		}
	}
	
	public ResponseEntity<?> editarUsuario(Usuario usuario) {
		if (usuario != null && usuario.getId() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Translator.toLocale("erro.editar.nao.salvar"));
		}
		try {
			usuario = usuarioRepository.save(usuario);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Translator.toLocale("erro.interno"));
		}
	}
	
	public ResponseEntity<Usuario> apagarUsuario(Integer id) {
		Optional<Usuario> usuario = buscaUsuario(id);
		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		try {
			usuarioRepository.delete(usuario.get());
			return ResponseEntity.ok(usuario.get());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	private Optional<Usuario> buscaUsuario(Integer id){
		return usuarioRepository.findById(id);
	}
}
