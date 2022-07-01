/*CAMADA DE SERVICO(SERVICE) - CATEGORIA --> ESSA CAMADA IRA ACESSAR A CAMADA REPOSITORY
 * A CAMADA REPOSITORY POR SUA VEZ IRA ACESSAR OS DADOS DO BANCO DE DADOS.
 */

package com.mmpcoder.praticando.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mmpcoder.praticando.domain.Categoria;
import com.mmpcoder.praticando.repositories.CategoriaRepository;
import com.mmpcoder.praticando.services.exceptions.DataIntegrityException;
import com.mmpcoder.praticando.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo; // ESSE COMANDO PARA ACESSAR A CAMADA REPOSITORY

	// PARA O METODO GET
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	// PARA O METODO POST
	public Categoria insert(Categoria obj) {
		obj.setId(null); // PARA GARANTIR QUE O ID A SER INSERIDO SERA NULL
		return repo.save(obj);
		
	}
	
	// PARA O METODO PUT
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	// PARA O METODO DELETAR
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não e possivel excluir uma categoria que possua produtos");
		}
	}
}
