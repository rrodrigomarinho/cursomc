package com.rodrigomarinho.cursomc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rodrigomarinho.cursomc.domain.Cidade;
import com.rodrigomarinho.cursomc.domain.Cliente;
import com.rodrigomarinho.cursomc.domain.Endereco;
import com.rodrigomarinho.cursomc.domain.enums.Perfil;
import com.rodrigomarinho.cursomc.domain.enums.TipoCliente;
import com.rodrigomarinho.cursomc.dto.ClienteDTO;
import com.rodrigomarinho.cursomc.dto.ClienteNewDTO;
import com.rodrigomarinho.cursomc.repositories.ClienteRepository;
import com.rodrigomarinho.cursomc.repositories.EnderecoRepository;
import com.rodrigomarinho.cursomc.security.UserSS;
import com.rodrigomarinho.cursomc.services.exceptions.MyAuthorizationException;
import com.rodrigomarinho.cursomc.services.exceptions.MyDataIntegrityViolationException;
import com.rodrigomarinho.cursomc.services.exceptions.MyObjectNotFoundException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefixo;
	
	public Cliente find(Integer id) throws ObjectNotFoundException {
		
		UserSS userSS = UserService.authenticated();
		if (userSS == null || !userSS.hasRole(Perfil.ADMIN) && !id.equals(userSS.getId())) {
			throw new MyAuthorizationException("Acesso negado");
		}
		
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new MyObjectNotFoundException("Objeto não encontrato! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}
	
	public Cliente update(Cliente cliente) throws ObjectNotFoundException {
		Cliente newCliente = find(cliente.getId());
		updateData(newCliente, cliente);
		return clienteRepository.save(newCliente);
	}
	
	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new MyDataIntegrityViolationException("Não é possível excluir esse cliente porque há entidades relacionadas.");
		}
	}
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage (Integer page, Integer linePerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO clienteNewDTO) {
		Cliente cliente = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteNewDTO.getTipoCliente()), bCryptPasswordEncoder.encode(clienteNewDTO.getSenha()));
		Cidade cidade = new Cidade(clienteNewDTO.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(), clienteNewDTO.getComplemento(), clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(clienteNewDTO.getTelefone1());
		if (clienteNewDTO.getTelefone2() != null) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone2());
		}
		if (clienteNewDTO.getTelefone3() != null) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone3());
		}
		return cliente;
	}
	
	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) throws ObjectNotFoundException {
		
		UserSS userSS = UserService.authenticated();
		if (userSS == null) {
			throw new MyAuthorizationException("Acesso negado");
		}
		
		BufferedImage bufferedImage = imageService.getJpgImageFromFile(multipartFile);
		String extensao = ".jpg";
		String fileName = prefixo.concat(userSS.getId().toString()).concat(extensao);
		
		return s3Service.uploadFile(imageService.getInputStream(bufferedImage, "jpg"), fileName, "image");
	}
}
