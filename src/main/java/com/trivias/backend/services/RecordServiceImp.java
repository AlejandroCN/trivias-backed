package com.trivias.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trivias.backend.model.dao.RecordDao;
import com.trivias.backend.model.entity.Record;

@Service
public class RecordServiceImp implements RecordService {
	
	@Autowired
	private RecordDao recordDao;

	@Override
	@Transactional
	public Record save(Record record) {
		return this.recordDao.save(record);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Record> findAllByUsuarioId(Long usuarioId) {
		return this.recordDao.findAllByUsuarioId(usuarioId);
	}

}
