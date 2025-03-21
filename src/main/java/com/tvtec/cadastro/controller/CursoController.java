package com.tvtec.cadastro.controller;

import com.tvtec.cadastro.model.Curso;
import com.tvtec.cadastro.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<Collection<Curso>> getAllCursos() {
        Collection<Curso> cursos = cursoService.getAllCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Curso>> getCursoById(@PathVariable("id") Long id) {
        Optional<Curso> curso = cursoService.getCurso(id);
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> addCurso(@RequestBody Curso curso) {
        Curso novoCurso = cursoService.addCurso(curso);
        return ResponseEntity.ok(novoCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable("id") Long id, @RequestBody Curso curso) {
        Curso cursoAtualizado = cursoService.updateCurso(id, curso);
        return ResponseEntity.ok(cursoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable("id") Long id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }
}
