//Classe que serve para restringir as informações que aprecem na API
package com.algaworks.algalog.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResumoModel {
	
	private Long id;
	private String nome;

}
