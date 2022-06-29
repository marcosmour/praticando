/*
 * CLASSE PARA MARCAR SE O CLIENTE E PESSOA FISICA OU 
 * PESSOA JURIDICA.
 */
package com.mmpcoder.praticando.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) { // O CONSTRUTOR PARA ENUN DEVE SER PRIVATE
		this.cod = cod;
		this.descricao = descricao;
	}
	
	// EM ENUM DEVE SER FEITO APENAS OS GETS
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente x: TipoCliente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
