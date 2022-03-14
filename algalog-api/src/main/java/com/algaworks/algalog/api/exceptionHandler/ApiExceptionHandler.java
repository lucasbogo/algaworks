package com.algaworks.algalog.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

@AllArgsConstructor //anotação para criar o construtor do private MessageSource messageSource
@ControllerAdvice //Anotação que diz que a classe é um componente do Spring  Com propósito específico de tratar excessões de forma global, ALL CONTROLLERS                
public class ApiExceptionHandler  extends ResponseEntityExceptionHandler{ 
	
	
	//Injetar o message.properties para receber mensagens em português
	private MessageSource messageSource; //É uma interface p/ resolver mensagens
	
	
	/*//Método Construtor p/ Messageource quando não tem lombok instalado
	public ApiExceptionHandler(MessageSource messageource) {
		super();
		this.messageSource = messageource;
	}*/



	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Problema.Campo> campos = new ArrayList<>(); //declarar lista e chamar a classe <Problema.campo>
		
		
		//Popular a lista pegando todos os erros que foram atribuidos na requisição
		//Cada erro pega da variável erros e joga no objeto objectError.
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {//GetAllErros retorna lista de object errors
		
			String nome = ((FieldError)error).getField();
			
			//Escolha do Locale para trabalhar com internacionalização
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			//Mostra mensagens de erro padrão
			//String mensagem = error.getDefaultMessage(); 
			
			
			//Adicionar a lista de campos declarada acima.
			campos.add(new Problema.Campo(nome,mensagem));
		}
		
		
		//Instanciação do objeto problema da classe Problema
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo("um ou mais campos estão inválidos. Faça o preenchimento correto");
		problema.setCampos(campos); //chamei o objeto campos declarado na List acima
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	//método para trata o exception mais específico EntidadeNaoEncontradaException
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handlEntidadeNaoEncontradaException(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;//404:NOT_FOUND
		
		//Instanciação do objeto problema da classe Problema
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(ex.getMessage());
		
		//1° parametro = propria ex vista em (NegocioException ex)
		//2° parametro = corpo da resposta
		//3° parametro = headers
		//4° parametro = status
		//5° parametro = request, (WebRequest request)
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request); 
		
	}
	
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;//400:BAD_REQUEST
		
		//Instanciação do objeto problema da classe Problema
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(ex.getMessage());
		
		//1° parametro = propria ex vista em (NegocioException ex)
		//2° parametro = corpo da resposta
		//3° parametro = headers
		//4° parametro = status
		//5° parametro = request, (WebRequest request)
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request); 
	}
		   
}
