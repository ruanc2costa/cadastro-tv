package com.tvtec.cadastro.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String professor;
    private Date dataInicio;
    private Date dataFim;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public Curso(Long id, String nome, String professor, Date dataInicio, Date dataFim) {
        this.id = id;
        this.nome = nome;
        this.professor = professor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Curso() {
    }

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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
