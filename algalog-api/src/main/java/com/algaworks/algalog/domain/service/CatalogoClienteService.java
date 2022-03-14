//Classe para regras de negócio
//serve para impedir código spaghetti no controlador; mantém o código mais organizado

package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class CatalogoClienteService {
	
	//Injetar repositório do cliente
	private ClienteRepository clienteRepository;
	
	//Refactor do código método solicitar cliente da classe SolicitacaoEntrega do pacote domain.service
	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId) //consultar se o cliente informado dentro da Entrega existe, pq, se ele não existe, atribuiremos a uma nova variável.
				.orElseThrow(() -> new NegocioException("cliente não encontrado"));//pega o que tem dentro do optional e atribui a variável cliente.caso contrário, joga  
	}
	
	
	
	
	/*//Construtor da injeção acima quando não se tem o lombok instalado.
	public CatalogoClienteService(ClienteRepository clienteRepository) {
		super();
		this.clienteRepository = clienteRepository;
	}*/
	
	
	
	
	
	//Método p/ salvar cliente
	@Transactional //Indica que esse meodo deve ser executado dentro de uma transação.
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()) //Faz busca pelo email retornando um optional
			.stream() //pesquisar recurso .stream() 
			//lógica: se o cliente encontrado no reposi. for diferente do cliente que estamos salvando
			//dará match = true(boolean) devio ao uso boolean emailuso = clienteRepository.findByEmail
			.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
	
		if(emailEmUso) { //caso o email esteja em uso, retorna:
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail");
		}
			
		return clienteRepository.save(cliente);//Caso contrário, salva cliente:		
	}
	
	//Método para excluir cliente
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);; //Injetar clienteRepository
	}
}
