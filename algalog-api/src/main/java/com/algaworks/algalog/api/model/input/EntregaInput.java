package com.algaworks.algalog.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaInput {

	//Referencia ao Id do cliente
	@Valid //faz o cascateamento das validações
	@NotNull
	private ClienteIdInput  cliente; //é um classe
	
	@Valid
	@NotNull
	private DestinatarioInput destinatario; //outra classe
	
	@NotNull
	private BigDecimal taxa;
}
