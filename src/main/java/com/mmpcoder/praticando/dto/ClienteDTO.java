/*
 * CLASSE CRIADA PARA PEGAR APENAS OS CAMPOS IMPORTANTES DE CLIENTE
 * APENAS OS CAMPOS DE QUE IMPORTAM NA HORA DE PEGAR AS IMFORMAÇÕES DE CLIENTE
 */
package com.mmpcoder.praticando.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mmpcoder.praticando.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Preencimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamamnho deve ser entre 5 e 120 caracteres" )
	private String nome;
	
	@NotEmpty(message="Preencimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	public ClienteDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
