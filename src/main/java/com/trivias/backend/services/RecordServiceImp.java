package com.trivias.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trivias.backend.model.dao.RecordDao;
import com.trivias.backend.model.entity.Record;
import com.trivias.backend.model.pojo.Pagina;

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
	
	@Override
	@Transactional(readOnly = true)
	public Page<Record> findAllPages(Pagina pagina) {
		Direction direction = Sort.Direction.ASC;
		if (!pagina.getDireccion()) {
			direction = Sort.Direction.DESC;
		}
		PageRequest pageable = PageRequest.of(pagina.getNumPagina(), pagina.getTamPagina(), direction,
				pagina.getAtributo());
		return this.recordDao.findAllPages(pageable, pagina.getTermino());
	}

	@Override
	@Transactional(readOnly = true)
	public Record findByUsuarioAndCategoria(Long usuarioId, Long categoriaId) {
		return this.recordDao.findByUsuarioAndCategoria(usuarioId, categoriaId);
	}

}
