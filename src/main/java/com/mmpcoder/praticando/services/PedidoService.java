/*CAMADA DE SERVICO(SERVICE) - CATEGORIA --> ESSA CAMADA IRA ACESSAR A CAMADA REPOSITORY
 * A CAMADA REPOSITORY POR SUA VEZ IRA ACESSAR OS DADOS DO BANCO DE DADOS.
 */

package com.mmpcoder.praticando.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmpcoder.praticando.domain.Pedido;
import com.mmpcoder.praticando.repositories.PedidoRepository;
import com.mmpcoder.praticando.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo; // ESSE COMANDO PARA ACESSAR A CAMADA REPOSITORY

	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
