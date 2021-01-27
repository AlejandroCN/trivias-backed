package com.trivias.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.trivias.backend.model.entity.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {
	
	public Usuario findByUsername(String username);

}
