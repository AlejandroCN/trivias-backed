package com.trivias.backend.services;

import com.trivias.backend.model.entity.Usuario;

public interface UsuarioService {

	public Usuario save(Usuario usuario);
	
	public Usuario findByUsername(String username);
	
}
