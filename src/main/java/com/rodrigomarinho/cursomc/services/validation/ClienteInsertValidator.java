package com.rodrigomarinho.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.rodrigomarinho.cursomc.domain.Cliente;
import com.rodrigomarinho.cursomc.domain.enums.TipoCliente;
import com.rodrigomarinho.cursomc.dto.ClienteNewDTO;
import com.rodrigomarinho.cursomc.repositories.ClienteRepository;
import com.rodrigomarinho.cursomc.resources.exceptions.FieldMessage;
import com.rodrigomarinho.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert clienteInsert) {
	}

	@Override
	public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (clienteNewDTO.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(clienteNewDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
		}
		
		if (clienteNewDTO.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(clienteNewDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido!"));
		}
		
		Cliente clienteEmail = clienteRepository.findByEmail(clienteNewDTO.getEmail());
		if (clienteEmail != null) {
			list.add(new FieldMessage("email", "Email já existente!"));
		}
		
		Cliente clienteCpfOuCnpj = clienteRepository.findByCpfOuCnpj(clienteNewDTO.getCpfOuCnpj());
		if (clienteCpfOuCnpj != null) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF/CNPJ já existente!"));
		}

		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}