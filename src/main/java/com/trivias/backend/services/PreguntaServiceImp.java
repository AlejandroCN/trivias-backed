package com.trivias.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trivias.backend.model.dao.PreguntaDao;
import com.trivias.backend.model.entity.Pregunta;

@Service
public class PreguntaServiceImp implements PreguntaService {

	@Autowired
	private PreguntaDao preguntaDao;
	
	@Override
	@Transactional
	public Pregunta save(Pregunta pregunta) {
		return this.preguntaDao.save(pregunta);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pregunta> findByCategoriaId(Long categoriaId) {
		return this.preguntaDao.findByCategoriaId(categoriaId);
	}

}
