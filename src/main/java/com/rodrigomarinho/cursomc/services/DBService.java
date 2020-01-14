package com.rodrigomarinho.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		Endereco end1 = new Endereco(null, "R. Itapuranga", "sn", "Quadra 08, Lote 24", "Bela Morada", "47920680", cli1, cid4);
		Endereco end2 = new Endereco(null, "R. J3", "sn", "Qd 35", "Setor Jaó", "01225810", cli2, cid5);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1));
		cli2.getEnderecos().addAll(Arrays.asList(end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
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
