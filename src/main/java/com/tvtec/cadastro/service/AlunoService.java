package com.tvtec.cadastro.service;

import com.tvtec.cadastro.model.Aluno;
import com.tvtec.cadastro.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Optional<Aluno> addAluno(Optional<Aluno> aluno) {
        if (aluno.isPresent()) {
            alunoRepository.save(aluno.get());
        }
        return aluno;
    }


    public Collection<Aluno> getAllAlunos(){
       return alunoRepository.findAll();
    }

    public Optional<Aluno> getAluno(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        return aluno;
    }

    public Aluno updateAluno(Long id, Aluno novoAluno) {
        return alunoRepository.findById(id).map(alunoExistente -> {
            alunoExistente.setNome(novoAluno.getNome());
            alunoExistente.setSobrenome(novoAluno.getSobrenome());
            alunoExistente.setSexo(novoAluno.getSexo());
            alunoExistente.setDataNascto(novoAluno.getDataNascto());
            return alunoRepository.save(alunoExistente);
        }).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o id " + id));
    }

    public void deleteAluno(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("Aluno não encontrado com o id " + id);
        }
        alunoRepository.deleteById(id);
    }
}
