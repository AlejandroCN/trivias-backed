package com.trivias.backend.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.trivias.backend.model.entity.Pregunta;
import com.trivias.backend.model.pojo.Pagina;

public interface PreguntaService {
	
	public Pregunta save(Pregunta pregunta);
	
	public Pregunta findById(Long id);
	
	public List<Pregunta> findByCategoriaId(Long categoriaId);
	
	public Page<Pregunta> findAllPages(Pagina pagina);
	
	public void delete(Long categoriaId);

}
