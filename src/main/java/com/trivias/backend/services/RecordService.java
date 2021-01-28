package com.trivias.backend.services;

import java.util.List;

import com.trivias.backend.model.entity.Record;

public interface RecordService {
	
	public Record save(Record record);
	
	public List<Record> findAllByUsuarioId(Long usuarioId);

}
