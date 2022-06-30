package com.mmpcoder.praticando.domain;

import javax.persistence.Entity;

import com.mmpcoder.praticando.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L; // FOI COLOCADO APENAS O NUMERO DO SERIALAZIBLE. PORQUE E UMA SUBCLASSE

	private Integer numeroDeParcelas;
	
	public PagamentoComCartao() {
		
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) { // COMO E UMA SUBCLASS FOI USADO O CONSTRUTOR DA SUPERCLASS E ADICIONADO OS OUTROS
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
	
}
