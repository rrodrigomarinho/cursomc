package com.rodrigomarinho.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigomarinho.cursomc.domain.Categoria;
import com.rodrigomarinho.cursomc.domain.Produto;
import com.rodrigomarinho.cursomc.repositories.CategoriaRepository;
import com.rodrigomarinho.cursomc.repositories.ProdutoRepository;
import com.rodrigomarinho.cursomc.services.exceptions.MyObjectNotFoundException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) throws ObjectNotFoundException {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new MyObjectNotFoundException("Objeto não encontrato! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linePerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		
//	 	Primeira maneira de fazer a consulta
//		return produtoRepository.search(nome, categorias, pageRequest);
		
//	 	Segunda maneira de fazer a consulta			
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}