package com.algaworks.algalog.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)//Inclui apena campos que NÃO estão nulos.
public class Problema {

	private Integer status;
	private OffsetDateTime dataHora; //OffsetDateTime= boa prática para trabalhar com data/hora pois segue o padrão de greenwitch time
	private String titulo;
	private List<Campo> campos;

	/*
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public OffsetDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(OffsetDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Campo> getCampos() {
		return campos;
	}

	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}
	*/

	@AllArgsConstructor
	@Getter //Só os getters são necessários nessa classe Campo detro da classe Problema
	public static class Campo { // Exemplo de classe dentro de uma classe. Criada para atender a List<Campo>

		private String nome;
		private String mensagem;

		/*
		//Getters
		public String getNome() {
			return nome;
		}
		
		//Getters
		public String getMensagem() {
			return mensagem;
		}

		//Constructor
		public Campo(String nome, String mensagem) {
			super();
			this.nome = nome;
			this.mensagem = mensagem;
		}*/

	}
   
}
