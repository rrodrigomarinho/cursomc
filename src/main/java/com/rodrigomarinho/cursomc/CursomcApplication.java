package com.rodrigomarinho.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rodrigomarinho.cursomc.domain.Categoria;
import com.rodrigomarinho.cursomc.domain.Cidade;
import com.rodrigomarinho.cursomc.domain.Cliente;
import com.rodrigomarinho.cursomc.domain.Endereco;
import com.rodrigomarinho.cursomc.domain.Estado;
import com.rodrigomarinho.cursomc.domain.Produto;
import com.rodrigomarinho.cursomc.domain.enums.TipoCliente;
import com.rodrigomarinho.cursomc.repositories.CategoriaRepository;
import com.rodrigomarinho.cursomc.repositories.CidadeRepository;
import com.rodrigomarinho.cursomc.repositories.ClienteRepository;
import com.rodrigomarinho.cursomc.repositories.EnderecoRepository;
import com.rodrigomarinho.cursomc.repositories.EstadoRepository;
import com.rodrigomarinho.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;


	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto pro1 = new Produto(null, "Computador", 2000.00);
		Produto pro2 = new Produto(null, "Impressora", 800.00);
		Produto pro3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(pro1, pro2, pro3));
		cat2.getProdutos().addAll(Arrays.asList(pro2));

		pro1.getCategorias().addAll(Arrays.asList(cat1));
		pro2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		pro3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(pro1, pro2, pro3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Estado est3 = new Estado(null, "Goiás");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		Cidade cid4 = new Cidade(null, "Aparecida de Goiânia", est3);
		Cidade cid5 = new Cidade(null, "Goiânia", est3);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		est3.getCidades().addAll(Arrays.asList(cid4));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3, cid4, cid5));
		
		Cliente cli1 = new Cliente(null, "Rodrigo", "rrodrigomarinho@gmail.com","75437708149", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("6233333333", "62982647784"));
		
		Cliente cli2 = new Cliente(null, "Pontta Sistemas", "suporte@pontta.com","05814922222", TipoCliente.PESSOA_JURIDICA);
		cli2.getTelefones().addAll(Arrays.asList("6233333333"));
		
		Endereco e1 = new Endereco(null, "R. Itapuranga", "sn", "Quadra 08, Lote 24", "Bela Morada", "47920680", cli1, cid4);
		Endereco e2 = new Endereco(null, "R. J3", "sn", "Qd 35", "Setor Jaó", "01225810", cli2, cid5);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1));
		cli2.getEnderecos().addAll(Arrays.asList(e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
}

