/*CAMADA DE SERVICO(SERVICE) - CATEGORIA --> ESSA CAMADA IRA ACESSAR A CAMADA REPOSITORY
 * A CAMADA REPOSITORY POR SUA VEZ IRA ACESSAR OS DADOS DO BANCO DE DADOS.
 */

package com.mmpcoder.praticando.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmpcoder.praticando.domain.Categoria;
import com.mmpcoder.praticando.repositories.CategoriaRepository;
import com.mmpcoder.praticando.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo; // ESSE COMANDO PARA ACESSAR A CAMADA REPOSITORY

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null); // PARA GARANTIR QUE O ID A SER INSERIDO SERA NULL
		return repo.save(obj);
		
	}
}
