package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
//import org.modelmapper.ModelMapper;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.EntregaAssembler;
import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController //dizer que é um controlador Rest, um componente spring
@RequestMapping("/entregas") //dizer que todos os métodos seguirá a base de mapeamento /entregas
public class EntregaController {
	

	private EntregaRepository entregaRepository;//Injeção de EntregaRepository.
	
	private SolicitacaoEntregaService solicitacaoEntregaService;//Injeção de Sol.Entre.Serv. localidao na domain service
	
	/*
	//é necessário configurar para tornar-se um bean gerenciador Spring
	//Substitui essa injeção pelo EtregaAssembler
	private ModelMapper modelMapper; //Injeção do ModelMapper que foi baixado por uma dependencia de pom.xml
	*/
	
	private EntregaAssembler entregaAssembler; //Injeção de EntregaAssembler
	
	private FinalizacaoEntregaService finalizacaoEntregaService;// chamar essa injeção no método void finalizar()
	
	
	
	/*
	@PostMapping //nova entrega fazer: post em /entregas passando no corpo da requisição um json com todos os dados de um entrega
	@ResponseStatus(HttpStatus.CREATED)//Resposta 201: CREATED
	public Entrega solicitar(@Valid @RequestBody Entrega entrega) { //Método que retorna uma entrega
		
		//passar para a classe de serviço e devolver a resposta.
		return solicitacaoEntregaService.solicitar(entrega);
		
	}
	*/
	
	//converter entregaModel para entregaAssembler novaEntrega
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
		
		//depois da conversão, passar uma novaEntrega no método solicitar que retorna uma entregaSolicitada.
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
		
		//aqui fazemos a conversão de volta para EntregaModel
		return entregaAssembler.toModel(entregaSolicitada);
	}
	
	
	/* Substituí esse código abaixo pelo código acima (método que retorna lista)
	public EntregaModel solicitar(@Valid @RequestBody Entrega entrega) {
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(entrega);
		return entregaAssembler.toModel(entregaSolicitada);
	}
	*/
	
	//Método refatorizado do código abaixo.Utilização da classe EntregaModel
	@GetMapping
	public List<EntregaModel> listar() {
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}
	
	
	/* Substituí esse código abaixo pelo código acima (método que retorna lista)
	@GetMapping
	public List<Entrega> listar() { //método para mostrar lista de entrega
		return entregaRepository.findAll();
	
	}	
	*/
	
	//Método refatorizado do código abaixo
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	/* Substituí esse código abaixo pelo código acima (refactoring) 
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega -> { //expressão lambda que recebe argumento entrega e, detro do bloco { }, implementa-se a conversão de Entega para EntregaModel
					EntregaModel entregaModel = modelMapper.map(entrega, EntregaModel.class);//aqui inserimos o tipo da conversão. 1° objeto de origem, nesse caso, entrega. 2° tipo de destino EntregaModel.class
					
					
					/*
					//Faremos esse código abaixo pelo ModelMapper
					EntregaModel entregaModel = new EntregaModel();
					Faremos esse código abaixo pelo ModelMapper
					//atribuir propriedades de EntregaModel
					entregaModel.setId(entrega.getId());// pega a Id da entidade do domainModel entrega e atribui a entregaModel.	
					entregaModel.setNomeCliente(entrega.getCliente().getNome());
					entregaModel.setDestinatario(new DestinatarioModel()); // indicar a classe vazia no entregaModel
					entregaModel.getDestinatario().setNome(entrega.getDestinatario().getNome()); //atribuir as propriedades de DestinatarioModel
					entregaModel.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
					entregaModel.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
					entregaModel.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
					entregaModel.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
					entregaModel.setTaxa(entrega.getTaxa());
					entregaModel.setStatus(entrega.getStatus());
					entregaModel.setDataPedido(entrega.getDataPedido());
					entregaModel.setDataFinalizacao(entrega.getDataFinalizacao()); 
					
						return ResponseEntity.ok(entregaModel);//retorna um responseEntity que mostra a situação das respostas e, no corpo, retorna a EntregaModel
				})
				.orElse(ResponseEntity.notFound().build());
	} 
	*/
	
	
	/*
	@GetMapping("/{entregaId}")
	public ResponseEntity<Entrega> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(ResponseEntity::ok)//caso exista algo dentro desse optional, retornamos um responseEntity ok com entrega dentro cdo optional como corpo da resposta
				.orElse(ResponseEntity.notFound().build());//se não existir nada dentro do optional que foi retornado pelo findById, retorna-se vazio 404 Not Found
	}
	*/
	
	//Faz um put em /entregas/idEntrega/finalizacao e chegará no método finalizar
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)// reponde com 204: NO_CONTENT
	public void finalizar(@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
		
	}

}

