// As validações de segurança aqui são opcionais pois estamos usando essa classe somente para mapeamento de objeto relacional

package com.algaworks.algalog.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.repository.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Entrega {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id; //equals and hash code generated
	
	@Valid //necessário para poder utilizar o @NotNull na classe cliente, propriedade Id e poder utilizar o message.properties.
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class) //configura-se essa anotação para converter de um validation group para outro.
	@NotNull //validation constraint não nulo
	@ManyToOne //entidade entrega, muitas entregas possuem um cliente
	private Cliente cliente;//propriedade classe Cliente: cliente associa-se a entrega, toda entrega tem um cliente
	
	@Valid
	@NotNull
	@Embedded //abstrair os dados e mantelos na mesma tabela
	private Destinatario destinatario;//propriedade classe Destinatario: destinatario associa-se a entrega...
	
	@NotNull
	private BigDecimal taxa;
	
	//RECEBE A PROPRIEDADE DONA DO RELACIONAMENTO
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL) //cascade =cascadeType.ALL serve para sinconizar as ocorrencias dentro do BD
	private List<Ocorrencia> ocorrencias = new ArrayList<>(); //Mapear Ocorrencia Entrega | Entrega Ocorrencia
	
	@JsonProperty(access = Access.READ_ONLY)//anotação jackson para indicar que o acesso é somente leitura, SEGURANÇA 
	@Enumerated(EnumType.STRING)//Tipo string. armazena no status da tabela a coluna a string que representa a constata da enumeration
	private StatusEntrega status;//Propridade enum StatusEntrega
	
	@JsonProperty(access = Access.READ_ONLY)//anotação jackson para indicar que o acesso é somente leitura, SEGURANÇA 
	private OffsetDateTime dataPedido; //OffsetDateTime= boa prática para trabalhar com data/hora pois segue o padrão de greenwitch time
	
	@JsonProperty(access = Access.READ_ONLY)//anotação jackson para indicar que o acesso é somente leitura, SEGURANÇA 
	private OffsetDateTime dataFinalizacao; //OffsetDateTime= boa prática para trabalhar com data/hora pois segue o padrão de greenwitch time

	public Ocorrencia adicionarOcorrencia(String descricao) {//método para adicionarOcorrencia
		
		//declarar uma variável do tipo ocorrencia
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);// é a propria descrição que foi recebida como argumento do método
		ocorrencia.setDataRegistro(OffsetDateTime.now());//data e hora atual
		ocorrencia.setEntrega(this);// this siginica que é a própria entrega  para qual estou adicionado a ocorrencia; Essa entrega = this
		
		//pegar o getOcorrencia que é o método getter da lista de ocorrencia encontrada nessa classe
		this.getOcorrencias().add(ocorrencia);//e adicona ocorrencia
		
		return ocorrencia; //retorna a ocorrencia que foi instanciada (public Ocorrencia ...)
	}

	public void finalizar() { // se o status não for pendente, lança-se uma exception NegocioException
		if (naoPodeSerFinalizada()) {
			throw new NegocioException("Entrega não pode ser finalizada");
			
		}
		
		setStatus(StatusEntrega.FINALIZADA);
		setDataFinalizacao(OffsetDateTime.now());
		
	}
	
	
	public boolean podeSerFinalizada() {
		return StatusEntrega.PENDENTE.equals(getStatus());
	}
	
	public boolean naoPodeSerFinalizada() {
		return !podeSerFinalizada();
	}
	
	
	
	/* lombok criar os getter, setters e construtores por anotação, assim despoluímos o código
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Destinatario getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}
	public BigDecimal getTaxa() {
		return taxa;
	}
	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}
	public StatusEntrega getStatus() {
		return status;
	}
	public void setStatus(StatusEntrega status) {
		this.status = status;
	}
	public LocalDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrega other = (Entrega) obj;
		return Objects.equals(id, other.id);
	}
	public LocalDateTime getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}
	*/
	
	
}
