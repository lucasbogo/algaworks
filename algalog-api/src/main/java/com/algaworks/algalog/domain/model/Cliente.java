//Desenvolver o representationalModel para separa certrinho na camada de API
//Assim não mistura-se as entidades do domain
//Fazer isso em toda aplicação

package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
//import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.algaworks.algalog.domain.repository.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;






@EqualsAndHashCode(onlyExplicitlyIncluded = true)// método construtor apenas para Id
@Getter//Anotações lombok para substituir o classico Getters and Setters.
@Setter//Anotações lombok para substituir o classico Getters and Setters.
@Entity // Pega o nome da classe e cria no BD como padrão
//@Table(name = "tb_cliente"): é possível criar um nome para a classe no BD
public class Cliente {
	
	
	@NotNull(groups = ValidationGroups.ClienteId.class)//
	@EqualsAndHashCode.Include //Incluir somente o id no equals and hash code.
	@Id // Essa anotação define a chave primária da entidade
	@GeneratedValue(strategy = GenerationType.IDENTITY)// Definir estratéria da geração da chave (AUTO-INCREMENT)
	private Long id;
	
	@NotBlank //validação: não pode ser deixado em branco e nulo
	@Size(max = 60)//Validação: tamnho máximo 60 caracteres
	private String nome;
	
	@NotBlank
	@Email
	@Size(max = 255)
	private String email;
	
	@NotBlank
	@Size(max = 20)
	@Column(name = "fone") // Especificar o nome da coluna.
	private String telefone;
	
	
	/*//Código boiler plate getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	//Código boiler plate equals and hashCode para o Id.
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
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	} 
	*/
	
}
