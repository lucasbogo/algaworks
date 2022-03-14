//classe que lança a exception 
//método que retorna ocorrencia Registrar uma ocorrencia
//que estava em RegistroOcorrencia
package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradaException;
//import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
	
	private EntregaRepository entregaRepository;

	public Entrega buscar(Long entregaId) { //método que retorna ocorrencia Registrar uma ocorrencia
		
		
	return entregaRepository.findById(entregaId) //Método receber um id de entrega e uma ocrrencia
			.orElseThrow(() -> new EntidadeNaoEncontradaException("entrega não encontrada"));//Negpcioexception que está sendo tratada na ApiExceptionHandler
					           //negocioExeception mais especializada												
	}
}
