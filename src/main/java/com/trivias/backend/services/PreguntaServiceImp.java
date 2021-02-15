package com.trivias.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trivias.backend.model.dao.PreguntaDao;
import com.trivias.backend.model.entity.Pregunta;
import com.trivias.backend.model.pojo.Pagina;

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
	public Pregunta findById(Long id) {
		return this.preguntaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pregunta> findByCategoriaId(Long categoriaId) {
		return this.preguntaDao.findByCategoriaId(categoriaId);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Pregunta> findAllPages(Pagina pagina) {
		Direction direction = Sort.Direction.ASC;
		if (!pagina.getDireccion()) {
			direction = Sort.Direction.DESC;
		}
		PageRequest pageable = PageRequest.of(pagina.getNumPagina(), pagina.getTamPagina(), direction,
				pagina.getAtributo());
		return this.preguntaDao.findAllPages(pageable, pagina.getTermino());
	}

	@Override
	@Transactional
	public void delete(Long categoriaId) {
		this.preguntaDao.deleteById(categoriaId);
	}

}
