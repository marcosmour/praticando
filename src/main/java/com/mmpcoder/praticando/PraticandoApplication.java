package com.mmpcoder.praticando;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mmpcoder.praticando.domain.Categoria;
import com.mmpcoder.praticando.domain.Cidade;
import com.mmpcoder.praticando.domain.Cliente;
import com.mmpcoder.praticando.domain.Endereco;
import com.mmpcoder.praticando.domain.Estado;
import com.mmpcoder.praticando.domain.ItemPedido;
import com.mmpcoder.praticando.domain.Pagamento;
import com.mmpcoder.praticando.domain.PagamentoComBoleto;
import com.mmpcoder.praticando.domain.PagamentoComCartao;
import com.mmpcoder.praticando.domain.Pedido;
import com.mmpcoder.praticando.domain.Produto;
import com.mmpcoder.praticando.domain.enums.EstadoPagamento;
import com.mmpcoder.praticando.domain.enums.TipoCliente;
import com.mmpcoder.praticando.repositories.CategoriaRepository;
import com.mmpcoder.praticando.repositories.CidadeRepository;
import com.mmpcoder.praticando.repositories.ClienteRepository;
import com.mmpcoder.praticando.repositories.EnderecoRepository;
import com.mmpcoder.praticando.repositories.EstadoRepository;
import com.mmpcoder.praticando.repositories.ItemPedidoRepository;
import com.mmpcoder.praticando.repositories.PagamentoRepository;
import com.mmpcoder.praticando.repositories.PedidoRepository;
import com.mmpcoder.praticando.repositories.ProdutoRepository;

@SpringBootApplication
public class PraticandoApplication implements CommandLineRunner{ // COMANDO PARA CRIAR AUTOMATICO NO BANCO DE DADOS. AULA 18
	
	@Autowired
	private CategoriaRepository categoriaRepository; // IMPLEMENTADO A CLASSE QUE FAZ O ACESSO COM O BANCO DE DADOS
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

	public static void main(String[] args) {
		SpringApplication.run(PraticandoApplication.class, args);
	}

@Override 
public void run(String... args) throws Exception {
	
	// PARA CATEGORIAS
	Categoria cat1 = new Categoria(null, "Informática");
	Categoria cat2 = new Categoria(null, "Escritório");
	
	Produto p1 = new Produto(null, "Computador", 2000.00);
	Produto p2 = new Produto(null, "Impressora", 800.00);
	Produto p3 = new Produto(null, "Mause", 80.00);
	
	cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3)); //PARA ADICIONAR OS PRODUTOS A CATEGORIA
	cat2.getProdutos().addAll(Arrays.asList(p2));
	
	p1.getCategorias().addAll(Arrays.asList(cat1)); // PARA AS CATEGORIAS CONHECER OS PRODUTOS
	p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
	p3.getCategorias().addAll(Arrays.asList(cat1));
	
	categoriaRepository.saveAll(Arrays.asList(cat1, cat2)); // PARA SALVAR NO BANCO DE DADOS. AULA 18
	produtoRepository.saveAll(Arrays.asList(p1, p2,p3));
	
	// PARA ESTADOS E CIDADES
	Estado est1 = new Estado(null, "Minas Gerais");
	Estado est2 = new Estado(null, "São Paulo");
	
	Cidade c1 = new Cidade(null, "Uberlândia", est1);
	Cidade c2 = new Cidade(null, "São Paulo", est2);
	Cidade c3 = new Cidade(null, "Campinas", est2);
	
	est1.getCidades().addAll(Arrays.asList(c1));
	est2.getCidades().addAll(Arrays.asList(c2, c3));
	
	estadoRepository.saveAll(Arrays.asList(est1, est2));
	cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
	
	// PARA CLIENTE, ENDERECO E TELEFONE
	Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA); // DEVER SER COLOCADO O TIPOCLIENTE.PJ OU PF
	
	cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393")); // PARA ADIONAR OS CONJUNTO DE TELEFONE 
	
	Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1); // PARA ADICIONAR O ENDERECO
	Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro","38777012", cli1, c2);
	
	cli1.getEnderecos().addAll(Arrays.asList(e1, e2)); // PARA O CLIENTE CONHECER OS ENDERECOS
	
	clienteRepository.saveAll(Arrays.asList(cli1));
	enderecoRepository.saveAll(Arrays.asList(e1, e2));
	
	// PARA OS PEDIDOS, PAGAMENTO
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // PARA USAR NO ATRIBUTO INSTANTE
	
	Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
	Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
	
	Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
	ped1.setPagamento(pagto1);
	
	Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
	ped2.setPagamento(pagto2);
	
	cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
	
	pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
	pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	
	// PARA PEDIDO, PRODUTO E ITENSPEDIDO
	
	ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
	ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
	ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
	
	ped1.getItens().addAll(Arrays.asList(ip1, ip2));
	ped2.getItens().addAll(Arrays.asList(ip3));
	
	p1.getItens().addAll(Arrays.asList(ip1));
	p2.getItens().addAll(Arrays.asList(ip3));
	p3.getItens().addAll(Arrays.asList(ip2));
	
	itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	
	
	
}

}
