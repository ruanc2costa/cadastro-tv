package com.tvtec.cadastro.service;

import com.tvtec.cadastro.model.Curso;
import com.tvtec.cadastro.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso addCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Collection<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> getCurso(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso updateCurso(Long id, Curso novoCurso) {
        return cursoRepository.findById(id).map(cursoExistente -> {
            cursoExistente.setNome(novoCurso.getNome());
            cursoExistente.setProfessor(novoCurso.getProfessor());
            cursoExistente.setDataInicio(novoCurso.getDataInicio());
            cursoExistente.setDataFim(novoCurso.getDataFim());
            return cursoRepository.save(cursoExistente);
        }).orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com o id " + id));
    }

    public void deleteCurso(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new EntityNotFoundException("Curso não encontrado com o id " + id);
        }
        cursoRepository.deleteById(id);
    }
}

