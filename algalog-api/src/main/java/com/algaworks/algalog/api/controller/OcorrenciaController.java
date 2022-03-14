package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.OcorrenciaAssembler;
import com.algaworks.algalog.api.model.OcorrenciaModel;
import com.algaworks.algalog.api.model.input.OcorrenciaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.service.BuscaEntregaService;
import com.algaworks.algalog.domain.service.RegistroOcorrenciaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

	private RegistroOcorrenciaService registroOcorrenciaService; //chama no return do método OcorrenciaModel
	
	private OcorrenciaAssembler ocorrenciaAssembler;//injeta-se ocorrenciaAssembler para chamar o toModel no return
	
	private BuscaEntregaService buscaEntregaService;//injeta-se BuscaEntregaService para utilizar no 
	
	@PostMapping //faremos um post na uri
	@ResponseStatus(HttpStatus.CREATED) //tendo sucesso ao criar a ocorrencia, a api retorna 201 created 
	public OcorrenciaModel registrar(@PathVariable Long entregaId,  //método
			@Valid @RequestBody OcorrenciaInput ocorrenciaInput) { 
		
	//retorna uma ocorrencia	      //return registroOcorrenciaService
	Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService
			.registrar(entregaId, ocorrenciaInput.getDescricao());
	
	//É necessário criar assembler (OcorrenciaAssembler) no pacote .assembler (feito!)
	//chamar o ocorrenciaAssembler.toModel passando como parâmento ocorrenciaRegistrada
	return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
	
	}
	
	@GetMapping()
	public List<OcorrenciaModel> listar(@PathVariable Long entregaId){
		
		Entrega entrega = buscaEntregaService.buscar(entregaId);//caso essa busca falhe, (não tenha uma entrega com essa Id), será lançada uma exception e, essa exception será tratada na exceptionHandler que retornará o 404.	
		
		return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());//aqui o jakarta persistence faz o select no BD sob demanda. [carregamento lazy]
	}
}
