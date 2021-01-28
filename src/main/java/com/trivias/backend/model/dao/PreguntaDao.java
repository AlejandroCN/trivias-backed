package com.trivias.backend.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trivias.backend.model.entity.Pregunta;

public interface PreguntaDao extends JpaRepository<Pregunta, Long> {
	
	@Query("select p from Pregunta p where p.categoria.id = ?1")
	public List<Pregunta> findByCategoriaId(Long categoriaId);

}
