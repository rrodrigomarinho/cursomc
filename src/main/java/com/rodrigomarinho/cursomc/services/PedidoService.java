package com.rodrigomarinho.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigomarinho.cursomc.domain.Pedido;
import com.rodrigomarinho.cursomc.repositories.PedidoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new com.rodrigomarinho.cursomc.services.exceptions.ObjectNotFoundException("Objeto não encontrato! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
