package com.trivias.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.trivias.backend.model.entity.Categoria;

public interface CategoriaDao extends CrudRepository<Categoria, Long> {

}
