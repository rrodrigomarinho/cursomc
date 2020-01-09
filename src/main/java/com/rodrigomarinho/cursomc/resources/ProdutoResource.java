package com.rodrigomarinho.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigomarinho.cursomc.domain.Categoria;
import com.rodrigomarinho.cursomc.domain.Produto;
import com.rodrigomarinho.cursomc.dto.CategoriaDTO;
import com.rodrigomarinho.cursomc.dto.ProdutoDTO;
import com.rodrigomarinho.cursomc.services.ProdutoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Produto obj = produtoService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") Integer nome, 
			@RequestParam(value="categorias", defaultValue="") Integer categorias, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linePerPage", defaultValue="24") Integer linePerPage, 
			@RequestParam(value="direction", defaultValue="ASC") String direction, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy) {
		Page<Produto> lista = produtoService.search(nome, categorias, page, linePerPage, direction, orderBy);
		Page<ProdutoDTO> listaDto = lista.map(produtoObj -> new ProdutoDTO(produtoObj));
		return ResponseEntity.ok().body(listaDto);
	}
} 
