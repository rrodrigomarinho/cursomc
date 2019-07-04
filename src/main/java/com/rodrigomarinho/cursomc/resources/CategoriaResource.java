package com.rodrigomarinho.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigomarinho.cursomc.domain.Categoria;
import com.rodrigomarinho.cursomc.services.CategoriaService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Categoria obj = categoriaService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {
		categoria = categoriaService.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Integer id) throws ObjectNotFoundException {
		categoria.setId(id);
		categoria = categoriaService.update(categoria);
		return ResponseEntity.noContent().build();
	}
} 
