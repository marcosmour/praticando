package com.mmpcoder.praticando;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mmpcoder.praticando.domain.Categoria;
import com.mmpcoder.praticando.domain.Produto;
import com.mmpcoder.praticando.repositories.CategoriaRepository;
import com.mmpcoder.praticando.repositories.ProdutoRepository;

@SpringBootApplication
public class PraticandoApplication implements CommandLineRunner{ // COMANDO PARA CRIAR AUTOMATICO NO BANCO DE DADOS. AULA 18
	
	@Autowired
	private CategoriaRepository categoriaRepository; // IMPLEMENTADO A CLASSE QUE FAZ O ACESSO COM O BANCO DE DADOS
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(PraticandoApplication.class, args);
	}

@Override 
public void run(String... args) throws Exception {
	
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
	
}

}
