package com.trivias.backend.restcontrollers;

import java.util.ArrayList;
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

import com.trivias.backend.model.entity.Pregunta;
import com.trivias.backend.services.PreguntaService;

@RestController
@RequestMapping("api/preguntas")
public class PreguntaRestController {

	@Autowired
	private PreguntaService preguntaService;
	
	@Secured("ROLE_ADMIN")
	@PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody Pregunta pregunta, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Pregunta preguntaCreada = null;

        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map((error) -> "Error en campo: " + error.getField() + ", error: " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<List<String>>(errores, HttpStatus.BAD_REQUEST);
        }

        try {
        	preguntaCreada = this.preguntaService.save(pregunta);
        } catch(DataAccessException ex) {
            response.put("mensaje", "Ocurrió un error interno al tratar de persistir la pregunta indicada");
            response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Pregunta>(preguntaCreada, HttpStatus.CREATED);
    }
	
	@Secured("ROLE_JUGADOR")
	@GetMapping("/find20RandomByCategoria/{categoriaId}")
	public ResponseEntity<?> find20RandomByCategoria(@PathVariable Long categoriaId) {
		Map<String, Object> response = new HashMap<>();
		List<Pregunta> preguntas = null;
		List<Pregunta> preguntasRandom = null;
		
		try {
			preguntas = this.preguntaService.findByCategoriaId(categoriaId);
			if (preguntas.size() == 0) {
				response.put("mensaje", "No existe ninguna pregunta registrada en la base de datos para la categoría indicada");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			} else if (preguntas.size() < 20) {
				response.put("mensaje", "No hay preguntas suficientes para realizar la trivia de la categoría indicada");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			} else if (preguntas.size() == 20) {
				return ResponseEntity.ok(preguntas);
			}
		} catch (DataAccessException ex) {
			response.put("mensaje", "Ocurrió un error interno al tratar de recuperar las preguntas solicitadas");
            response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		int totalPreguntas = preguntas.size();
		preguntasRandom = new ArrayList<Pregunta>();
		while (preguntasRandom.size() < 20) {
			int indicePreguntaRandom = (int) Math.floor(Math.random() * totalPreguntas);
			if(!preguntasRandom.contains(preguntas.get(indicePreguntaRandom)))
				preguntasRandom.add(preguntas.get(indicePreguntaRandom));
		}
		return ResponseEntity.ok(preguntasRandom);
	}
	
}
