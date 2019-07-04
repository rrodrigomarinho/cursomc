package com.rodrigomarinho.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigomarinho.cursomc.domain.Cliente;
import com.rodrigomarinho.cursomc.repositories.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new com.rodrigomarinho.cursomc.services.exceptions.MyObjectNotFoundException("Objeto não encontrato! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
