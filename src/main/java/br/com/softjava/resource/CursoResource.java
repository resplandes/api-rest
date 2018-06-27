package br.com.softjava.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.softjava.modelo.Curso;

@RestController
public class CursoResource {

	private Map<Integer, Curso> cursos;

	public CursoResource() {
		cursos = new HashMap<Integer, Curso>();

		Curso c1 = new Curso(1, "Workshop Rest", "24hs");
		Curso c2 = new Curso(2, "Workshop Spring MVC", "24hs");
		Curso c3 = new Curso(3, "Desenvolvimento Web com JSF 2", "60hs");

		cursos.put(1, c1);
		cursos.put(2, c2);
		cursos.put(3, c3);
	}

	@RequestMapping(value = "/cursos", method = RequestMethod.GET)
	public ResponseEntity<List<Curso>> listar() {
		return new ResponseEntity<List<Curso>>(new ArrayList<Curso>(cursos.values()), HttpStatus.OK);
	}

	@RequestMapping(value = "/cursos/{id}", method = RequestMethod.GET)
	public ResponseEntity<Curso> buscar(@PathVariable("id") Integer id) {
		Curso curso = cursos.get(id);

		if (curso == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Curso>(curso, HttpStatus.OK);
	}

	@RequestMapping(value = "/cursos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") int id) {
		Curso curso = cursos.remove(id);

		if (curso == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}