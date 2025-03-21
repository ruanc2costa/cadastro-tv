package com.tvtec.cadastro.controller;

import com.tvtec.cadastro.model.Aluno;
import com.tvtec.cadastro.model.Curso;
import com.tvtec.cadastro.repository.AlunoRepository;
import com.tvtec.cadastro.service.AlunoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    private ResponseEntity<Collection<Aluno>> getAllAlunos(){
        Collection<Aluno> alunos = alunoService.getAllAlunos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<Aluno>> getAlunoById(@PathVariable("id") Long id) {
        Optional<Aluno> aluno = alunoService.getAluno(id);
        return ResponseEntity.ok(aluno);
    }

    @PostMapping("/alunos")
    public ResponseEntity<Aluno> postAlunos(@RequestBody Aluno alunoRequest) {
        Optional<Aluno> optionalAluno = alunoRepository.findByEmail(alunoRequest.getEmail());
        Aluno aluno;

        Curso cursoSelecionado = alunoRequest.getCursos().get(0);

        if (optionalAluno.isPresent()) {
            aluno = optionalAluno.get();
            if (!aluno.getCursos().contains(cursoSelecionado)) {
                aluno.getCursos().add(cursoSelecionado);
                alunoRepository.save(aluno);
            }
        } else {
            aluno = new Aluno();
            aluno.setNome(alunoRequest.getNome());
            aluno.setEmail(alunoRequest.getEmail());

            List<Curso> cursos = new ArrayList<>();
            cursos.add(cursoSelecionado);
            aluno.setCursos(cursos);

            alunoRepository.save(aluno);
        }

        return ResponseEntity.ok(aluno);
    }


    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteAluno(@PathVariable("id") Long id) {
        alunoService.deleteAluno(id);
        return ResponseEntity.noContent().build();
    }

}
