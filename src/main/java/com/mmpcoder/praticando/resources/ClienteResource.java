/*CAMADA CONTROLADOR REST(RESOURCE) - CATEGORIA --> ESSSA CAMADA IRA RETORNAR PARA APLICAÇÃO CLIENTE OS METODOS DE PESQUISA, GET, POST, PUT. ETC.
 *ESSA CLASSE IRA ACESSAR A CAMADA SERVICO,
 *E A CAMADA SERVICO IRA ACESSAR A CAMADA REPOSITORY,
 *QUE A CAMADA RESOPITORY IRA ACESSAR O BANCO DE DADOS
 *CTRL+F - PARA SUBSTUIR OS NOMES
 */

package com.mmpcoder.praticando.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmpcoder.praticando.domain.Cliente;
import com.mmpcoder.praticando.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service; // ESSE COMANDO PARA ESSA CAMADA ACESSAR A CAMADA DE SERVICO

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) { // @PATHVARIABLE PARA INDICAR QUE O ID DA REQUISIÇÃO SERA O MESMO DOS PARENTESES
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}
