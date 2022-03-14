//Classe para tratamento de exces~oes muito mais específico
//é um negócioException mais especializada
package com.algaworks.algalog.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException {

	
	private static final long serialVersionUID = 1L;

	//construtor
	public EntidadeNaoEncontradaException(String message) {
		super(message);
		
	}

}
