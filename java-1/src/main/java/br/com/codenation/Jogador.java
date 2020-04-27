package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {
  private final long id;
  private final long idTime;
  private final String nome;
  private final LocalDate dataNascimento;
  private final Integer nivelHabilidade;
  private final BigDecimal salario;

  public Jogador(
      long id,
      long idTime,
      String nome,
      LocalDate dataNascimento,
      Integer nivelHabilidade,
      BigDecimal salario) {
    this.id = id;
    this.idTime = idTime;
    this.nome = nome;
    this.dataNascimento = dataNascimento;
    this.nivelHabilidade = nivelHabilidade;
    this.salario = salario;
  }

  public long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public Integer getNivelHabilidade() {
    return nivelHabilidade;
  }

  public BigDecimal getSalario() {
    return salario;
  }

  public long getIdTime() {
    return idTime;
  }
}
