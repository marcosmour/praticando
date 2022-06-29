/*CAMADA DE SERVICO(SERVICE) - CLIENTE --> ESSA CAMADA IRA ACESSAR A CAMADA REPOSITORY
 * A CAMADA REPOSITORY POR SUA VEZ IRA ACESSAR OS DADOS DO BANCO DE DADOS.
 * SERA CRIANDA SEMPRE QUE FORMOS CRIAR O ENDPOINT
 * CTRL+F - PARA SUBSTITUIR OS NOMES
 */

package com.mmpcoder.praticando.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmpcoder.praticando.domain.Cliente;
import com.mmpcoder.praticando.repositories.ClienteRepository;
import com.mmpcoder.praticando.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo; // ESSE COMANDO PARA ACESSAR A CAMADA REPOSITORY

	public Cliente buscar(Integer id) { // IRA RECEBER UM ID E RETORNAR O CLIENTE COM ESSE ID
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
