//classe para o caso de uso de criaçao de entrega
package com.algaworks.algalog.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor //anotação para criar contrutor automaticamente
@Service //anotação para dizer que é um componente spring com semantica de um servico
public class SolicitacaoEntregaService {
	
	//injeção do repositório pelo construtor (AllArgsConstructor)
	private EntregaRepository entregaRepository;
	
	//injeção do repositóro Cliente
	//private ClienteRepository clienteRepository; foi feito refatoramento dessa injeção de classe.
	
	private CatalogoClienteService catalogoClienteService;
	
	//Método refatorizado do mesmo código abaixo desse.
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
	
	
	//Foi feito refatoramento desse código na classe CatalogoClienteService(ver codigo acima)
	/*//método que retorna uma entrega, necessário importar .model.entrega
	@Transactional //anotação de transação por ser uma classe de serviço
	public Entrega solicitar(Entrega entrega) {
	Cliente cliente = clienteRepository.findById(entrega.getCliente().getId()) //consultar se o cliente informado dentro da Entrega existe, pq, se ele não existe, atribuiremos a uma nova variável.
		.orElseThrow(() -> new NegocioException("cliente não encontrado"));//pega o que tem dentro do optional e atribui a variável cliente.caso contrário, joga  */
		
		entrega.setCliente(cliente);// resolve o porblema de retorna o cliente com as propriedades vazias
		entrega.setStatus(StatusEntrega.PENDENTE);//toda solcitação de entrega entra como status pendente
		entrega.setDataPedido(OffsetDateTime.now());
		return entregaRepository.save(entrega);
		
	}
}
