/*CLASSE PARA RETORNAR UM ERRO PERSONALIZADO CASO NÃO SEJA ENCONTRADO 
 * O ID NO BANCO DE DADOS
 */
package com.mmpcoder.praticando.services.exceptions;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
