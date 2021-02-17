package com.trivias.backend.model.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trivias.backend.model.entity.Record;

public interface RecordDao extends JpaRepository<Record, Long> {

	@Query("select r from Record r where r.usuario.id = ?1")
	public List<Record> findAllByUsuarioId(Long usuarioId);
	
	@Query("select r from Record r"
			+ " where r.categoria.categoria like %?1%"
			+ " or r.fecha like %?1%")
	public Page<Record> findAllPages(Pageable pageable, String termino);
	
	@Query("select r from Record r where r.usuario.id = ?1 and r.categoria.id = ?2")
	public Record findByUsuarioAndCategoria(Long usuarioId, Long categoriaId);
	
}
