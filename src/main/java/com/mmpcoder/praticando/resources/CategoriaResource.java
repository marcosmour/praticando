/*CAMADA CONTROLADOR REST(RESOURCE) - CATEGORIA --> ESSSA CAMADA IRA RETORNAR PARA APLICAÇÃO CLIENTE OS METODOS DE PESQUISA, GET, POST, PUT. ETC.
 *ESSA CLASSE IRA ACESSAR A CAMADA SERVICO,
 *E A CAMADA SERVICO IRA ACESSAR A CAMADA REPOSITORY,
 *QUE A CAMADA RESOPITORY IRA ACESSAR O BANCO DE DADOS
 */

package com.mmpcoder.praticando.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mmpcoder.praticando.domain.Categoria;
import com.mmpcoder.praticando.dto.CategoriaDTO;
import com.mmpcoder.praticando.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service; // ESSE COMANDO PARA ESSA CAMADA ACESSAR A CAMADA DE SERVICO

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) { // @PATHVARIABLE PARA INDICAR QUE O ID DA REQUISIÇÃO SERA O MESMO DOS PARENTESES
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){ // PARA CONVERTER OS DADOS DE JSON PARA OBJETO // METODO ATUALIZADO NA AULA 39 PARA VALIDAR OS CAMPOS NA HORA DE INSERIR
		Categoria obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); // PARA PEGAR A URI DO RECURSO INSERIDO. AULA 34. 07:00
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id){
		Categoria obj = service.fromDTO(objDto);
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
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<Categoria> list = service.findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList()); // PARA CONVERTER UMA LISTA PARA OUTRA LISTA AULA 37. 08:00
		return ResponseEntity.ok().body(listDto);
	}
	
	// PAR FAZER UMA BUSCA PAGINADA. AULA 38
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction){
		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj)); // PARA CONVERTER UMA LISTA PARA OUTRA LISTA AULA 37. 08:00
		return ResponseEntity.ok().body(listDto);
	}
	
	
}
