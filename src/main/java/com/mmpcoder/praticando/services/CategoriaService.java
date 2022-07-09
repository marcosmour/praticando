/*CAMADA DE SERVICO(SERVICE) - CATEGORIA --> ESSA CAMADA IRA ACESSAR A CAMADA REPOSITORY
 * A CAMADA REPOSITORY POR SUA VEZ IRA ACESSAR OS DADOS DO BANCO DE DADOS.
 */

package com.mmpcoder.praticando.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mmpcoder.praticando.domain.Categoria;
import com.mmpcoder.praticando.dto.CategoriaDTO;
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
		Categoria newObj = find(obj.getId()); // CODIGO ALTERADO NA AULA 41. 13:15
		updateData(newObj, obj); // METODO CRIADO NA PARTE DE BAIXO DESSA CLASSE
		return repo.save(newObj);
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
	
	// PARA BUSCAR TODAS AS CATEGORIAS
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	// PARA FAZER UMA BUSCA COM PAGINACAO. AULA 38
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
		
	}
}
