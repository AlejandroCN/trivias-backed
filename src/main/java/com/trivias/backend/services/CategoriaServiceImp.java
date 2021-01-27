package com.trivias.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trivias.backend.model.dao.CategoriaDao;
import com.trivias.backend.model.entity.Categoria;

@Service
public class CategoriaServiceImp implements CategoriaService {
	
	@Autowired
	private CategoriaDao categoriaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return (List<Categoria>) this.categoriaDao.findAll();
	}

}
