package com.trivias.backend.services;

import java.util.List;

import com.trivias.backend.model.entity.Pregunta;

public interface PreguntaService {
	
	public Pregunta save(Pregunta pregunta);
	
	public List<Pregunta> findByCategoriaId(Long categoriaId);

}
