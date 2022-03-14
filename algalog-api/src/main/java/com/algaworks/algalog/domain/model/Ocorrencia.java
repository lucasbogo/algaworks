package com.algaworks.algalog.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity //anotações do jpa para trabalhar com BD.
public class Ocorrencia {
	
	@EqualsAndHashCode.Include
	@Id //Mapeamento Id do JavaxPersistence
	@GeneratedValue(strategy = GenerationType.IDENTITY) //generated value com estrategia IDENTITY
	private Long id;
	
	@ManyToOne //Relação muitas para um. Geralmente explícito em diagramas de classe. (muitas ocorrencia têm uma entrega)
	private Entrega entrega;
	
	//Não precisa de mapeamento pois a coluno no BD continuara sendo descricao
	private String descricao;
	
	//Não precisa de mapeamento pois a coluno no BD continuara sendo dataRegistro
	private OffsetDateTime dataRegistro;

}
