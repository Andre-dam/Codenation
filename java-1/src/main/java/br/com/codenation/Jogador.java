package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {
    long id;
    long idTime;
    String nome;
    LocalDate dataNascimento;
    Integer nivelHabilidade;
    BigDecimal salario;

    public Jogador(long id, long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nivelHabilidade = nivelHabilidade;
        this.salario = salario;
    }
}