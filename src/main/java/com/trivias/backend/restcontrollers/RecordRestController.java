package com.trivias.backend.restcontrollers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trivias.backend.model.entity.Record;
import com.trivias.backend.services.RecordService;

@RestController
@RequestMapping("api/records")
public class RecordRestController {
	
	@Autowired
	private RecordService recordService;
	
	@Secured("ROLE_USUARIO")
	@PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody Record record, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Record recordCreado = null;

        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map((error) -> "Error en campo: " + error.getField() + ", error: " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<List<String>>(errores, HttpStatus.BAD_REQUEST);
        }

        try {
        	recordCreado = this.recordService.save(record);
        } catch(DataAccessException ex) {
            response.put("mensaje", "Ocurrió un error interno al tratar de persistir el record indicado");
            response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Record>(recordCreado, HttpStatus.CREATED);
    }
	
	@Secured("ROLE_USUARIO")
	@GetMapping("/findAllByUsuario/{usuarioId}")
	public ResponseEntity<?> findAllByUsuario(@PathVariable Long usuarioId) {
		Map<String, Object> response = new HashMap<>();
		List<Record> records = null;
		
		try {
			records = this.recordService.findAllByUsuarioId(usuarioId);
			if (records.size() == 0) {
				response.put("mensaje", "No existe ningún record registrado en la base de datos para el usuario indicado");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
        } catch(DataAccessException ex) {
            response.put("mensaje", "Ocurrió un error interno al tratar de recuperar los records solicitados");
            response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
		return ResponseEntity.ok(records);
	}

}
