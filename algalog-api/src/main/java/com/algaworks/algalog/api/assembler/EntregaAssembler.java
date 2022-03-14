package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component // instruir que a classe é um componente do Spring
public class EntregaAssembler {
	
	private ModelMapper modelMapper;
	
	public EntregaModel toModel(Entrega entrega) {
		
		return modelMapper.map(entrega, EntregaModel.class); //Usa-se esse método no controlador entrega (EntregaController)
	}
	
	//Método que resulta em um list de EntregaModel
	public List<EntregaModel> toCollectionModel(List<Entrega> entregas) {
		return entregas.stream() //.stream suporta um fluxo de elementos de agregação e transformação
				.map(this::toModel) //Aqui converte um stream de entregas para um stream de entregasModel
				.collect(Collectors.toList()); //Aqui converte um stream para uma lista; reduz um stream para uma coleção usando um collector (Collectors.toList)
 	}
	
	// Trocar de EntregaInput para uma Entrega
	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
		
	}

}
