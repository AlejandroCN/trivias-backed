package com.trivias.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trivias.backend.model.dao.CategoriaDao;
import com.trivias.backend.model.entity.Categoria;
import com.trivias.backend.model.pojo.Pagina;

@Service
public class CategoriaServiceImp implements CategoriaService {
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	@Override
	@Transactional
	public Categoria save(Categoria categoria) {
		return this.categoriaDao.save(categoria);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return (List<Categoria>) this.categoriaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Categoria> findAllPages(Pagina pagina) {
		Direction direction = Sort.Direction.ASC;
		if (!pagina.getDireccion()) {
			direction = Sort.Direction.DESC;
		}
		PageRequest pageable = PageRequest.of(pagina.getNumPagina(), pagina.getTamPagina(), direction,
				pagina.getAtributo());
		return this.categoriaDao.findAllPages(pageable, pagina.getTermino());
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria findById(Long id) {
		return this.categoriaDao.findById(id).orElse(null);
	}

}
