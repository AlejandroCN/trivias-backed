package com.trivias.backend.restcontrollers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trivias.backend.model.entity.Categoria;
import com.trivias.backend.model.pojo.Pagina;
import com.trivias.backend.services.CategoriaService;

@RestController
@RequestMapping("api/categorias")
public class CategoriaRestController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody Categoria categoria, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Categoria categoriaCreada = null;

        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map((error) -> "Error en campo: " + error.getField() + ", error: " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<List<String>>(errores, HttpStatus.BAD_REQUEST);
        }

        try {
        	categoriaCreada = this.categoriaService.save(categoria);
        } catch(DataAccessException ex) {
            response.put("mensaje", "Ocurrió un error interno al tratar de persistir categoría");
            response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Categoria>(categoriaCreada, HttpStatus.CREATED);
    }
	
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
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/findAllPages")
	public ResponseEntity<?> findAllPages(@RequestBody Pagina pagina) {
		Map<String, Object> response = new HashMap<>();
		Page<Categoria> categorias = null;
		
		try {
			categorias = this.categoriaService.findAllPages(pagina);
			if (categorias.getNumberOfElements() == 0) {
				response.put("mensaje", "No existe ninguna categoría en la página solicitada");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException ex) {
			response.put("mensaje", "Ocurrió un error interno");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(categorias);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Categoria categoriaExistente = null;
		
		try {
			categoriaExistente = this.categoriaService.findById(id);
			if (categoriaExistente == null) {
				response.put("mensaje", "La categoría solicitada no existe");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException ex) {
			response.put("mensaje", "Ocurrió un error interno");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(categoriaExistente);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("")
    public ResponseEntity<?> update(@Valid @RequestBody Categoria categoria, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Categoria categoriaExistente = null;

        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map((error) -> "Error en campo: " + error.getField() + ", error: " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<List<String>>(errores, HttpStatus.BAD_REQUEST);
        }

        try {
        	categoriaExistente = this.categoriaService.findById(categoria.getId());
        	if (categoriaExistente == null) {
        		response.put("mensaje", "La categoría que se intenta actualizar no existe");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        	}
        	
        	categoriaExistente.setCategoria(categoria.getCategoria());
        	categoriaExistente.setDescripcion(categoria.getDescripcion());
        	categoriaExistente.setImagen(categoria.getImagen());
        	this.categoriaService.save(categoriaExistente);
        } catch(DataAccessException ex) {
            response.put("mensaje", "Ocurrió un error interno al tratar de actualizar categoría");
            response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Categoria>(categoriaExistente, HttpStatus.CREATED);
    }
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
        Categoria categoriaExistente = null;
        
        try {
        	categoriaExistente = this.categoriaService.findById(id);
        	if (categoriaExistente == null) {
        		response.put("mensaje", "La categoría que se intenta eliminar no existe");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        	}
        	if (this.categoriaService.tieneDependencias(id)) {
        		response.put("mensaje", "No puede eliminar la categoría indicada dado que tiene asociaciones");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        	}
        	this.categoriaService.delete(id);
        } catch(DataAccessException ex) {
            response.put("mensaje", "Ocurrió un error interno al tratar de actualizar categoría");
            response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("mensaje", "Categoría eliminada correctamente!");
        return ResponseEntity.ok(response);
	}

}
