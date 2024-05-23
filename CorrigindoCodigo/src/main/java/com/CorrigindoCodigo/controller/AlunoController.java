package com.CorrigindoCodigo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CorrigindoCodigo.entities.Aluno;
import com.CorrigindoCodigo.service.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")

public class AlunoController {
    
    private final AlunoService alunoService;
    
    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
    //Query Method
    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<Aluno>> buscarAlunosPorCidade(@PathVariable String cidade) {
      List<Aluno> alunos = alunoService.buscarAlunosPorCidade(cidade);
      return ResponseEntity.ok(alunos);
    }
    
    //@query
    @GetMapping("/ra/{ra}")
    public Aluno findAlunosPorRa(@PathVariable String ra) {
        return alunoService.buscarAlunosPorRa(ra);
    }
   
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getProductById(@PathVariable Long id) {
        Aluno aluno = alunoService.getAlunoById(id);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    public ResponseEntity<List<Aluno>> getAllAluno() {
        List<Aluno> aluno = alunoService.getAllAluno();
        return ResponseEntity.ok(aluno);
    }

    @PostMapping("/")
    public ResponseEntity<Aluno> criarAluno(@RequestBody @Valid Aluno aluno) {
        Aluno criarAluno = alunoService.salvarAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarAluno);
    }
   

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody @Valid Aluno aluno) {
        Aluno updatedAluno = alunoService.updateAluno(id, aluno);
        if (updatedAluno != null) {
            return ResponseEntity.ok(updatedAluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAluno(@PathVariable Long id) {
        boolean deleted = alunoService.deleteAluno(id);
        if (deleted) {
        	return ResponseEntity.ok().body("A Turma foi excluído com sucesso.");  
        } else {
            return ResponseEntity.notFound().build();
        }
    }
       
 }
