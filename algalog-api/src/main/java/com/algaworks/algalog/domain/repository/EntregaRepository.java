package com.algaworks.algalog.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algalog.domain.model.Entrega;

//JpaRepository: 1° especifica oo tipo da entidade que estamos gerenciamento através desse repositório
//JpaRepository: 2° especifica o tipo do Id, nesse caso, é Long
@Repository // anotação para declarar que a interface é um repositório
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

}
