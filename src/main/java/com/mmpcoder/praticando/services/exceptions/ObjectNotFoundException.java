/*CLASSE PARA RETORNAR UM ERRO PERSONALIZADO CASO NÃO SEJA ENCONTRADO 
 * O ID NO BANCO DE DADOS
 */
package com.mmpcoder.praticando.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
