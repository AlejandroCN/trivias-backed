package com.trivias.backend.restcontrollers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trivias.backend.model.entity.Categoria;
import com.trivias.backend.services.CategoriaService;

@RestController
@RequestMapping("api/categorias")
public class CategoriaRestController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Secured({"ROLE_JUGADOR", "ROLE_ADMIN"})
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		Map<String, Object> response = new HashMap<>();
		List<Categoria> categorias = null;
		
		try {
			categorias = this.categoriaService.findAll();
			if (categorias.size() == 0) {
				response.put("mensaje", "No existe ninguna categoría registrada en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException ex) {
			response.put("mensaje", "Ocurrió un error interno");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(categorias);
	}

}
