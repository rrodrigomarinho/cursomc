package com.rodrigomarinho.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rodrigomarinho.cursomc.domain.Categoria;
import com.rodrigomarinho.cursomc.domain.Cidade;
import com.rodrigomarinho.cursomc.domain.Cliente;
import com.rodrigomarinho.cursomc.domain.Endereco;
import com.rodrigomarinho.cursomc.domain.Estado;
import com.rodrigomarinho.cursomc.domain.ItemPedido;
import com.rodrigomarinho.cursomc.domain.Pagamento;
import com.rodrigomarinho.cursomc.domain.PagamentoComBoleto;
import com.rodrigomarinho.cursomc.domain.PagamentoComCartao;
import com.rodrigomarinho.cursomc.domain.Pedido;
import com.rodrigomarinho.cursomc.domain.Produto;
import com.rodrigomarinho.cursomc.domain.enums.EstadoPagamento;
import com.rodrigomarinho.cursomc.domain.enums.Perfil;
import com.rodrigomarinho.cursomc.domain.enums.TipoCliente;
import com.rodrigomarinho.cursomc.repositories.CategoriaRepository;
import com.rodrigomarinho.cursomc.repositories.CidadeRepository;
import com.rodrigomarinho.cursomc.repositories.ClienteRepository;
import com.rodrigomarinho.cursomc.repositories.EnderecoRepository;
import com.rodrigomarinho.cursomc.repositories.EstadoRepository;
import com.rodrigomarinho.cursomc.repositories.ItemPedidoRepository;
import com.rodrigomarinho.cursomc.repositories.PagamentoRepository;
import com.rodrigomarinho.cursomc.repositories.PedidoRepository;
import com.rodrigomarinho.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void instantiateTestDataBase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto pro1 = new Produto(null, "Computador", 2000.00);
		Produto pro2 = new Produto(null, "Impressora", 800.00);
		Produto pro3 = new Produto(null, "Mouse", 80.00);
		
		Produto pro4 = new Produto(null, "Mesa", 300.00);
		Produto pro5 = new Produto(null, "Toalha", 50.00);
		Produto pro6 = new Produto(null, "Colcha", 200.00);
		Produto pro7 = new Produto(null, "TV True Color", 1200.00);
		Produto pro8 = new Produto(null, "Roçadeira", 800.00);
		Produto pro9 = new Produto(null, "Abajour", 100.00);
		Produto pro10 = new Produto(null, "Pendente", 180.00);
		Produto pro11 = new Produto(null, "Shampoo", 90.00);
		
		Produto p12 = new Produto(null, "Produto 12", 10.00);
		Produto p13 = new Produto(null, "Produto 13", 10.00);
		Produto p14 = new Produto(null, "Produto 14", 10.00);
		Produto p15 = new Produto(null, "Produto 15", 10.00);
		Produto p16 = new Produto(null, "Produto 16", 10.00);
		Produto p17 = new Produto(null, "Produto 17", 10.00);
		Produto p18 = new Produto(null, "Produto 18", 10.00);
		Produto p19 = new Produto(null, "Produto 19", 10.00);
		Produto p20 = new Produto(null, "Produto 20", 10.00);
		Produto p21 = new Produto(null, "Produto 21", 10.00);
		Produto p22 = new Produto(null, "Produto 22", 10.00);
		Produto p23 = new Produto(null, "Produto 23", 10.00);
		Produto p24 = new Produto(null, "Produto 24", 10.00);
		Produto p25 = new Produto(null, "Produto 25", 10.00);
		Produto p26 = new Produto(null, "Produto 26", 10.00);
		Produto p27 = new Produto(null, "Produto 27", 10.00);
		Produto p28 = new Produto(null, "Produto 28", 10.00);
		Produto p29 = new Produto(null, "Produto 29", 10.00);
		Produto p30 = new Produto(null, "Produto 30", 10.00);
		Produto p31 = new Produto(null, "Produto 31", 10.00);
		Produto p32 = new Produto(null, "Produto 32", 10.00);
		Produto p33 = new Produto(null, "Produto 33", 10.00);
		Produto p34 = new Produto(null, "Produto 34", 10.00);
		Produto p35 = new Produto(null, "Produto 35", 10.00);
		Produto p36 = new Produto(null, "Produto 36", 10.00);
		Produto p37 = new Produto(null, "Produto 37", 10.00);
		Produto p38 = new Produto(null, "Produto 38", 10.00);
		Produto p39 = new Produto(null, "Produto 39", 10.00);
		Produto p40 = new Produto(null, "Produto 40", 10.00);
		Produto p41 = new Produto(null, "Produto 41", 10.00);
		Produto p42 = new Produto(null, "Produto 42", 10.00);
		Produto p43 = new Produto(null, "Produto 43", 10.00);
		Produto p44 = new Produto(null, "Produto 44", 10.00);
		Produto p45 = new Produto(null, "Produto 45", 10.00);
		Produto p46 = new Produto(null, "Produto 46", 10.00);
		Produto p47 = new Produto(null, "Produto 47", 10.00);
		Produto p48 = new Produto(null, "Produto 48", 10.00);
		Produto p49 = new Produto(null, "Produto 49", 10.00);
		Produto p50 = new Produto(null, "Produto 50", 10.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		p12.getCategorias().add(cat1);
		p13.getCategorias().add(cat1);
		p14.getCategorias().add(cat1);
		p15.getCategorias().add(cat1);
		p16.getCategorias().add(cat1);
		p17.getCategorias().add(cat1);
		p18.getCategorias().add(cat1);
		p19.getCategorias().add(cat1);
		p20.getCategorias().add(cat1);
		p21.getCategorias().add(cat1);
		p22.getCategorias().add(cat1);
		p23.getCategorias().add(cat1);
		p24.getCategorias().add(cat1);
		p25.getCategorias().add(cat1);
		p26.getCategorias().add(cat1);
		p27.getCategorias().add(cat1);
		p28.getCategorias().add(cat1);
		p29.getCategorias().add(cat1);
		p30.getCategorias().add(cat1);
		p31.getCategorias().add(cat1);
		p32.getCategorias().add(cat1);
		p33.getCategorias().add(cat1);
		p34.getCategorias().add(cat1);
		p35.getCategorias().add(cat1);
		p36.getCategorias().add(cat1);
		p37.getCategorias().add(cat1);
		p38.getCategorias().add(cat1);
		p39.getCategorias().add(cat1);
		p40.getCategorias().add(cat1);
		p41.getCategorias().add(cat1);
		p42.getCategorias().add(cat1);
		p43.getCategorias().add(cat1);
		p44.getCategorias().add(cat1);
		p45.getCategorias().add(cat1);
		p46.getCategorias().add(cat1);
		p47.getCategorias().add(cat1);
		p48.getCategorias().add(cat1);
		p49.getCategorias().add(cat1);
		p50.getCategorias().add(cat1);
		
		cat1.getProdutos().addAll(Arrays.asList(pro1, pro2, pro3));
		cat2.getProdutos().addAll(Arrays.asList(pro2, pro4));
		cat3.getProdutos().addAll(Arrays.asList(pro5, pro6));
		cat4.getProdutos().addAll(Arrays.asList(pro1, pro1, pro3, pro7));
		cat5.getProdutos().addAll(Arrays.asList(pro8));
		cat6.getProdutos().addAll(Arrays.asList(pro9, pro10));
		cat7.getProdutos().addAll(Arrays.asList(pro11));

		pro1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		pro2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		pro3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		pro4.getCategorias().addAll(Arrays.asList(cat2));
		pro5.getCategorias().addAll(Arrays.asList(cat3));
		pro6.getCategorias().addAll(Arrays.asList(cat3));
		pro7.getCategorias().addAll(Arrays.asList(cat4));
		pro8.getCategorias().addAll(Arrays.asList(cat5));
		pro9.getCategorias().addAll(Arrays.asList(cat6));
		pro10.getCategorias().addAll(Arrays.asList(cat6));
		pro11.getCategorias().addAll(Arrays.asList(cat7));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(pro1, pro2, pro3, pro4, pro5, pro6, pro7, pro8, pro9, pro10, pro11));
		
		produtoRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
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
		
		Cliente cli1 = new Cliente(null, "Rodrigo", "rrodrigomarinho@gmail.com","75437708149", TipoCliente.PESSOA_FISICA, bCryptPasswordEncoder.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("6233333333", "62982647784"));
		cli1.addPerfil(Perfil.CLIENTE);
		
		Cliente cli2 = new Cliente(null, "Pontta Sistemas Rodrigo", "rodrigo@pontta.com","05814922222", TipoCliente.PESSOA_JURIDICA, bCryptPasswordEncoder.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("6233333333"));
		cli2.addPerfil(Perfil.CLIENTE);
		
		Cliente cli3 = new Cliente(null, "Lorezo", "lorenzoalves@gmail.com","69050029094", TipoCliente.PESSOA_FISICA, bCryptPasswordEncoder.encode("123"));
		cli3.getTelefones().addAll(Arrays.asList("6255555555"));
		cli3.addPerfil(Perfil.ADMIN);
		
		Endereco end1 = new Endereco(null, "R. Itapuranga", "sn", "Quadra 08, Lote 24", "Bela Morada", "47920680", cli1, cid4);
		Endereco end2 = new Endereco(null, "R. J3", "sn", "Qd 35", "Setor Jaó", "01225810", cli2, cid5);
		Endereco end3 = new Endereco(null, "Avenida K2", "123", "Qd 25", "Setor Dos Afonsos", "01225810", cli3, cid5);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1));
		cli2.getEnderecos().addAll(Arrays.asList(end2));
		cli3.getEnderecos().addAll(Arrays.asList(end3));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");  
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2019 10:30"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("30/09/2019 19:35"), cli1, end2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2018 00:00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		
		ItemPedido ip1 = new ItemPedido(ped1, pro1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, pro3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, pro2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		pro1.getItens().addAll(Arrays.asList(ip1));
		pro2.getItens().addAll(Arrays.asList(ip3));
		pro3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
