package com.algaworks.algalog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

//import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Entrega;
//import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {
	
	private EntregaRepository entregaRepository;
	
	private BuscaEntregaService buscaEntregaService;
	
	//metodo para finalizar uma entrega
	@Transactional
	public void finalizar(Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		entrega.finalizar();  //injeçao do método finalizar que está dentro da classe Entrega 
			
		
		
		/*//Esse método funciona, mas faremos de outra maneira, com a injeção do método finalizar dentro da classe Entrega
		//se não for pendente lança-se uma excessão (NegocioException)
		if(!entrega.getStatus().equals(StatusEntrega.PENDENTE)) {
			throw new NegocioException("Entrega não pode ser finalizada");
			
		}
		
		entrega.setStatus(StatusEntrega.FINALIZADA); */
		
		entregaRepository.save(entrega);
		
	}

	
}
