/*CAMADA CONTROLADOR REST(RESOURCE) - CATEGORIA --> ESSSA CAMADA IRA RETORNAR PARA APLICAÇÃO CLIENTE OS METODOS DE PESQUISA, GET, POST, PUT. ETC.
 *ESSA CLASSE IRA ACESSAR A CAMADA SERVICO,
 *E A CAMADA SERVICO IRA ACESSAR A CAMADA REPOSITORY,
 *QUE A CAMADA RESOPITORY IRA ACESSAR O BANCO DE DADOS
 */

package com.mmpcoder.praticando.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmpcoder.praticando.domain.Pedido;
import com.mmpcoder.praticando.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service; // ESSE COMANDO PARA ESSA CAMADA ACESSAR A CAMADA DE SERVICO

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id) { // @PATHVARIABLE PARA INDICAR QUE O ID DA REQUISIÇÃO SERA O MESMO DOS PARENTESES
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
