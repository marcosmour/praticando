/*CAMADA DE SERVICO(SERVICE) - CLIENTE --> ESSA CAMADA IRA ACESSAR A CAMADA REPOSITORY
 * A CAMADA REPOSITORY POR SUA VEZ IRA ACESSAR OS DADOS DO BANCO DE DADOS.
 * SERA CRIANDA SEMPRE QUE FORMOS CRIAR O ENDPOINT
 * CTRL+F - PARA SUBSTITUIR OS NOMES
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

import com.mmpcoder.praticando.domain.Cliente;
//import com.mmpcoder.praticando.domain.Cliente;
import com.mmpcoder.praticando.dto.ClienteDTO;
import com.mmpcoder.praticando.repositories.ClienteRepository;
import com.mmpcoder.praticando.services.exceptions.DataIntegrityException;
import com.mmpcoder.praticando.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo; // ESSE COMANDO PARA ACESSAR A CAMADA REPOSITORY

	public Cliente find(Integer id) { // IRA RECEBER UM ID E RETORNAR O CLIENTE COM ESSE ID
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	// PARA O METODO PUT
		public Cliente update(Cliente obj) {
			Cliente newObj = find(obj.getId()); // CODIGO ALTERADO NA AULA 41. 13:15
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
				throw new DataIntegrityException("Não e possivel excluir porque há entidades relacionadas");
			}
		}
		
		// PARA BUSCAR TODAS AS CATEGORIAS
		public List<Cliente> findAll(){
			return repo.findAll();
		}
		
		// PARA FAZER UMA BUSCA COM PAGINACAO. AULA 38
		public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return repo.findAll(pageRequest);
			
		}
		
		public Cliente fromDTO(ClienteDTO objDto) {
			return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
		}
		
		private void updateData(Cliente newObj, Cliente obj) {
			newObj.setNome(obj.getNome());
			newObj.setEmail(obj.getEmail());
		}
}
