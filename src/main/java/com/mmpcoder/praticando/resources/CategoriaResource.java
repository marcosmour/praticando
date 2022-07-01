/*CAMADA CONTROLADOR REST(RESOURCE) - CATEGORIA --> ESSSA CAMADA IRA RETORNAR PARA APLICAÇÃO CLIENTE OS METODOS DE PESQUISA, GET, POST, PUT. ETC.
 *ESSA CLASSE IRA ACESSAR A CAMADA SERVICO,
 *E A CAMADA SERVICO IRA ACESSAR A CAMADA REPOSITORY,
 *QUE A CAMADA RESOPITORY IRA ACESSAR O BANCO DE DADOS
 */

package com.mmpcoder.praticando.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mmpcoder.praticando.domain.Categoria;
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
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){ // PARA CONVERTER OS DADOS DE JSON PARA OBJETO
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); // PARA PEGAR A URI DO RECURSO INSERIDO. AULA 34. 07:00
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
}
