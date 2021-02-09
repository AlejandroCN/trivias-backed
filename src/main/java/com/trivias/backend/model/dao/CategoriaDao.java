package com.trivias.backend.model.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trivias.backend.model.entity.Categoria;

public interface CategoriaDao extends JpaRepository<Categoria, Long> {

	@Query("select c from Categoria c"
			+ " where c.categoria like %?1%"
			+ " or c.descripcion like %?1%")
	public Page<Categoria> findAllPages(Pageable pageable, String termino);
	
}
