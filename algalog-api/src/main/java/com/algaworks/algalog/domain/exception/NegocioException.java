package com.algaworks.algalog.domain.exception;

public class NegocioException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	//Método construtor que passa a mensagem para a classe pai, nesse caso, a RunTimeException
	public NegocioException(String message) {
		super(message);
	}
}
