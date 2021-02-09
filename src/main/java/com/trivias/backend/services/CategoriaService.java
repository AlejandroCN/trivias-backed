package com.trivias.backend.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.trivias.backend.model.entity.Categoria;
import com.trivias.backend.model.pojo.Pagina;

public interface CategoriaService {
	
	public Categoria save(Categoria categoria);
	
	public List<Categoria> findAll();
	
	public Page<Categoria> findAllPages(Pagina pagina);
	
	public Categoria findById(Long id);

}
