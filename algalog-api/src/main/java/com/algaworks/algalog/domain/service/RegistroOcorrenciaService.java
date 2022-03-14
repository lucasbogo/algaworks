package com.algaworks.algalog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

//import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
//import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
	
	//Essa injeçao não é mais necessário pois criamos a classe BuscaEntregaService 
	//private EntregaRepository entregaRepository; //injeção de entregaRepository
	
	private BuscaEntregaService buscaEntregaService; //injeção de buscaEntregaService
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = buscaEntregaService.buscar(entregaId); //caso o id não exista, a exception vai vir do método buscar e a mesma passará para frente.
		
		return entrega.adicionarOcorrencia(descricao);
	}

	/*esse código foi refagtorizado e está acima desse código
	//método que retorna ocorrencia Registrar uma ocorrencia
	@Transactional //Serve para colocar dentro de uma transação
	public Ocorrencia registrar(Long entregaId, String descricao) {  //Método receber um id de entrega e uma ocrrencia
		Entrega entrega = entregaRepository.findById(entregaId)
		.orElseThrow(() -> new NegocioException("entrega não encontrada")); 
	
		                                           //pegar a entrega e adicionar uma nova ocorrencia
	return	entrega.adicionarOcorrencia(descricao);//chamar método adicionarOcorrencia no tipo entrega passando a descriao
		                                           //método adicionarOcorrencia deve instanciar um objeto do tipo ocorrência e adiconar na lista 
		
	
	} */
}
