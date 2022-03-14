package com.algaworks.algalog.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //anotação para configuração de beans
public class ModelMapperConfig {
	
	//adiciona-se métodos que definem beans Spring: Códifo abaixo
	
	@Bean //indica que o método ModelMapper incializa e configura um bean que será gerenciado pelo Spring para poder ser injetado em outrs classes
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
