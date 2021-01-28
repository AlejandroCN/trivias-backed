package com.trivias.backend.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trivias.backend.model.entity.Record;

public interface RecordDao extends JpaRepository<Record, Long> {

	@Query("select r from Record r where r.usuario.id = ?1")
	public List<Record> findAllByUsuarioId(Long usuarioId);
	
}
