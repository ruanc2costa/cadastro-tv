package com.tvtec.cadastro.repository;

import com.tvtec.cadastro.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByEmail(String email);
    Optional<Aluno> findByTelefone(String telefone);
}
