/*CAMADA CONTROLADOR REST(RESOURCE) - CATEGORIA --> ESSSA CAMADA IRA RETORNAR PARA APLICAÇÃO CLIENTE OS METODOS DE PESQUISA, GET, POST, PUT. ETC.
 *ESSA CLASSE IRA ACESSAR A CAMADA SERVICO,
 *E A CAMADA SERVICO IRA ACESSAR A CAMADA REPOSITORY,
 *QUE A CAMADA RESOPITORY IRA ACESSAR O BANCO DE DADOS
 *CTRL+F - PARA SUBSTUIR OS NOMES
 */

package com.mmpcoder.praticando.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmpcoder.praticando.domain.Cliente;
import com.mmpcoder.praticando.dto.ClienteDTO;
import com.mmpcoder.praticando.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service; // ESSE COMANDO PARA ESSA CAMADA ACESSAR A CAMADA DE SERVICO

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) { // @PATHVARIABLE PARA INDICAR QUE O ID DA REQUISIÇÃO SERA O MESMO DOS PARENTESES
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	// PARA BUSCAR TODAS AS CATEGORIAS
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList()); // PARA CONVERTER UMA LISTA PARA OUTRA LISTA AULA 37. 08:00
		return ResponseEntity.ok().body(listDto);
	}
	
	// PAR FAZER UMA BUSCA PAGINADA. AULA 38
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction){
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj)); // PARA CONVERTER UMA LISTA PARA OUTRA LISTA AULA 37. 08:00
		return ResponseEntity.ok().body(listDto);
	}
}
