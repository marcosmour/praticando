package com.mmpcoder.praticando;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mmpcoder.praticando.domain.Categoria;
import com.mmpcoder.praticando.repositories.CategoriaRepository;

@SpringBootApplication
public class PraticandoApplication implements CommandLineRunner{ // COMANDO PARA CRIAR AUTOMATICO NO BANCO DE DADOS. AULA 18
	
	@Autowired
	private CategoriaRepository categoriaRepository; // IMPLEMENTADO A CLASSE QUE FAZ O ACESSO COM O BANCO DE DADOS

	public static void main(String[] args) {
		SpringApplication.run(PraticandoApplication.class, args);
	}

@Override 
public void run(String... args) throws Exception {
	
	Categoria cat1 = new Categoria(null, "Informática");
	Categoria cat2 = new Categoria(null, "Escritório");
	
	categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	
}

}
