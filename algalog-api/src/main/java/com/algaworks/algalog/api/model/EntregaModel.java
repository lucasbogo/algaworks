package com.algaworks.algalog.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.algalog.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaModel {

	private Long id;
	//private String nomeCliente; 
	private ClienteResumoModel cliente; // Cria-se essa classe para não gter que mostrar todas as propriedades do cliente
	private DestinatarioModel destinatario; //Classe separada para organização de código, Está no mesmo pacote
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
}
