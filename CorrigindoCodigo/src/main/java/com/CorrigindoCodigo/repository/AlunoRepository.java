package com.CorrigindoCodigo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CorrigindoCodigo.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	//Query Methods
	List<Aluno> findByCidade(String cidade);
	Aluno findByRa(String ra);

}
