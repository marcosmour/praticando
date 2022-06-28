package com.mmpcoder.praticando;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mmpcoder.praticando.domain.Categoria;
import com.mmpcoder.praticando.domain.Cidade;
import com.mmpcoder.praticando.domain.Estado;
import com.mmpcoder.praticando.domain.Produto;
import com.mmpcoder.praticando.repositories.CategoriaRepository;
import com.mmpcoder.praticando.repositories.CidadeRepository;
import com.mmpcoder.praticando.repositories.EstadoRepository;
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
	
}

}
