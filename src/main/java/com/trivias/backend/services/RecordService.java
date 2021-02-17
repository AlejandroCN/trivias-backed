package com.trivias.backend.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.trivias.backend.model.entity.Record;
import com.trivias.backend.model.pojo.Pagina;

public interface RecordService {
	
	public Record save(Record record);
	
	public List<Record> findAllByUsuarioId(Long usuarioId);
	
	public Page<Record> findAllPages(Pagina pagina);
	
	public Record findByUsuarioAndCategoria(Long usuarioId, Long categoriaId);

}
